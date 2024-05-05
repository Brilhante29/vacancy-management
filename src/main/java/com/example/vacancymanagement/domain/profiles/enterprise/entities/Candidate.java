package com.example.vacancymanagement.domain.profiles.enterprise.entities;

import com.example.vacancymanagement.core.entities.Entity;
import com.example.vacancymanagement.core.entities.UniqueEntityID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Candidate extends Entity {
    private String name;
    private String email;
    private String phone;
    private String resumeLink;
    private String areaOfInterest;

    @Builder
    public Candidate(UniqueEntityID id, String name, String email, String phone, String resumeLink, String areaOfInterest) {
        super(id);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.resumeLink = resumeLink;
        this.areaOfInterest = areaOfInterest;
    }
}
