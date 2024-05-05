package com.example.vacancymanagement.core.entities;

import com.example.vacancymanagement.core.events.DomainEvent;
import com.example.vacancymanagement.core.events.DomainEvents;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AggregateRoot extends Entity {
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected AggregateRoot(UniqueEntityID id) {
        super(id);
    }

    protected void addDomainEvent(DomainEvent domainEvent) {
        domainEvents.add(domainEvent);
        DomainEvents.markAggregateForDispatch(this);
    }

    public void clearEvents() {
        domainEvents.clear();
    }
}
