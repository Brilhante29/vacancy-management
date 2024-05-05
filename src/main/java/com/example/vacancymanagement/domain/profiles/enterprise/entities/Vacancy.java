package com.example.vacancymanagement.domain.profiles.enterprise.entities;

import com.example.vacancymanagement.core.entities.Entity;
import com.example.vacancymanagement.core.entities.UniqueEntityID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Vacancy extends Entity {
    private String title;
    private String description;
    private String requirements;
    private Double salary;
    private String status;
    private Date publicationDate;
    private Date closingDate;
    private UniqueEntityID companyId;
    private UniqueEntityID recruiterId; // Link to the recruiter

    @Builder
    public Vacancy(UniqueEntityID id, String title, String description, String requirements, Double salary, String status, Date publicationDate, Date closingDate, UniqueEntityID companyId, UniqueEntityID recruiterId) {
        super(id);
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.salary = salary;
        this.status = status;
        this.publicationDate = publicationDate;
        this.closingDate = closingDate;
        this.companyId = companyId;
        this.recruiterId = recruiterId; // Assign recruiter ID
    }
}