package com.example.vacancymanagement.domain.profiles.application.dtos;

public record CompanyResponse(
        String name,
        String sector,
        String description,
        String location
) {}