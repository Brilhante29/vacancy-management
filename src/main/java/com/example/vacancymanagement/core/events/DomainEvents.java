package com.example.vacancymanagement.core.events;

import com.example.vacancymanagement.core.entities.AggregateRoot;
import com.example.vacancymanagement.core.entities.UniqueEntityID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class DomainEvents {
    private static final Map<Class<? extends DomainEvent>, List<Consumer<DomainEvent>>> handlersMap = new HashMap<>();
    private static final List<AggregateRoot> markedAggregates = new ArrayList<>();

    public static void markAggregateForDispatch(AggregateRoot aggregate) {
        if (!markedAggregates.contains(aggregate)) {
            markedAggregates.add(aggregate);
        }
    }

    public static void dispatchEventsForAggregate(UniqueEntityID id) {
        AggregateRoot aggregate = findMarkedAggregateByID(id);
        if (aggregate != null) {
            aggregate.getDomainEvents().forEach(DomainEvents::dispatch);
            aggregate.clearEvents();
            markedAggregates.remove(aggregate);
        }
    }

    private static void dispatch(DomainEvent event) {
        List<Consumer<DomainEvent>> handlers = handlersMap.get(event.getClass());
        if (handlers != null) {
            handlers.forEach(handler -> handler.accept(event));
        }
    }

    private static AggregateRoot findMarkedAggregateByID(UniqueEntityID id) {
        return markedAggregates.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    public static <T extends DomainEvent> void registerHandler(Class<T> eventType, Consumer<T> handler) {
        handlersMap.computeIfAbsent(eventType, k -> new ArrayList<>())
                .add(event -> {
                    if (eventType.isInstance(event)) {
                        handler.accept(eventType.cast(event));
                    }
                });
    }

    public static void clearHandlers() {
        handlersMap.clear();
    }

    public static void clearMarkedAggregates() {
        markedAggregates.clear();
    }
}
