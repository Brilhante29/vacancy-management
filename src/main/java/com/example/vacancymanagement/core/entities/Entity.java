package com.example.vacancymanagement.core.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

@Getter
@Setter
public abstract class Entity {
    @NonNull
    private UniqueEntityID id;

    public Entity(@NonNull UniqueEntityID id) {
        this.id = id;
    }
}
