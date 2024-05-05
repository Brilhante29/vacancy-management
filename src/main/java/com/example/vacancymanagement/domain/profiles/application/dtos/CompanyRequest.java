package com.example.vacancymanagement.domain.profiles.application.dtos;

public record CompanyRequest(
        String name,
        String sector,
        String description,
        String location
) {}