package com.example.vacancymanagement.domain.profiles.enterprise.entities;

import com.example.vacancymanagement.core.entities.Entity;
import com.example.vacancymanagement.core.entities.UniqueEntityID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Recruiter extends Entity {
    private String name;
    private String email;
    private UniqueEntityID companyId; // Link to the company

    @Builder
    public Recruiter(UniqueEntityID id, String name, String email, UniqueEntityID companyId) {
        super(id);
        this.name = name;
        this.email = email;
        this.companyId = companyId; // Assign company ID
    }
}