package com.example.vacancymanagement.core.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
public abstract class WatchedList<T> {
    @Setter
    private List<T> currentItems;
    private final List<T> initial;
    private List<T> newItems = new ArrayList<>();
    private List<T> removed = new ArrayList<>();
    private final Set<T> existenceCache = new HashSet<>();

    public WatchedList(List<T> initialItems) {
        this.initial = new ArrayList<>(initialItems);
        this.currentItems = new ArrayList<>(initialItems);
    }

    protected abstract boolean compareItems(T a, T b);

    public void add(T item) {
        if (!exists(item)) {
            currentItems.add(item);
            newItems.add(item);
            existenceCache.add(item);
        }
    }

    public void remove(T item) {
        if (exists(item)) {
            currentItems.removeIf(v -> compareItems(v, item));
            removed.add(item);
            existenceCache.remove(item);
        }
    }

    public void update(List<T> items) {
        Set<T> itemsSet = new HashSet<>(items);
        Set<T> currentSet = new HashSet<>(currentItems);

        Set<T> newItems = new HashSet<>(itemsSet);
        newItems.removeAll(currentSet);

        Set<T> removedItems = new HashSet<>(currentSet);
        removedItems.removeAll(itemsSet);

        this.currentItems.clear();
        this.currentItems.addAll(items);
        this.newItems = new ArrayList<>(newItems);
        this.removed = new ArrayList<>(removedItems);

        // Update the existence cache
        existenceCache.clear();
        existenceCache.addAll(items);
    }

    public boolean exists(T item) {
        if (existenceCache.contains(item)) {
            return true;
        }
        boolean result = currentItems.stream().anyMatch(v -> compareItems(v, item));
        existenceCache.add(item);  // Cache the result
        return result;
    }
}