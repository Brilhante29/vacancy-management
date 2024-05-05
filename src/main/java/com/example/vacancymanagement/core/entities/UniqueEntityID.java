package com.example.vacancymanagement.core.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
public class UniqueEntityID {
    private final UUID value;

    public UniqueEntityID(UUID value) {
        this.value = value != null ? value : UUID.randomUUID();
    }

    public UniqueEntityID() {
        this(null);
    }
}
