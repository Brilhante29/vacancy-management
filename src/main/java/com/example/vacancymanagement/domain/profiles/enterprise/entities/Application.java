package com.example.vacancymanagement.domain.profiles.enterprise.entities;


import com.example.vacancymanagement.core.entities.Entity;
import com.example.vacancymanagement.core.entities.UniqueEntityID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Application extends Entity {
    private UniqueEntityID candidateId;
    private UniqueEntityID vacancyId;
    private Date applicationDate;
    private String status;

    @Builder
    public Application(UniqueEntityID id, UniqueEntityID candidateId, UniqueEntityID vacancyId, Date applicationDate, String status) {
        super(id);
        this.candidateId = candidateId;
        this.vacancyId = vacancyId;
        this.applicationDate = applicationDate;
        this.status = status;
    }
}
