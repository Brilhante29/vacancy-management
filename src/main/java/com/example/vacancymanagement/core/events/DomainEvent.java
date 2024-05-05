package com.example.vacancymanagement.core.events;

import com.example.vacancymanagement.core.entities.UniqueEntityID;

import java.util.Date;

public interface DomainEvent {
    Date getOccurredAt();
    UniqueEntityID getAggregateId();
}