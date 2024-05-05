package com.example.vacancymanagement.domain.profiles.application.dtos;

public record CandidateRequest(
        String name,
        String email,
        String phone,
        String resumeLink,
        String areaOfInterest
) {}