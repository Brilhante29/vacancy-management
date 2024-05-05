package com.example.vacancymanagement.domain.profiles.enterprise.entities;

import com.example.vacancymanagement.core.entities.Entity;
import com.example.vacancymanagement.core.entities.UniqueEntityID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company extends Entity {
    private String name;
    private String sector;
    private String description;
    private String location;

    @Builder
    public Company(UniqueEntityID id, String name, String sector, String description, String location) {
        super(id);
        this.name = name;
        this.sector = sector;
        this.description = description;
        this.location = location;
    }
}
