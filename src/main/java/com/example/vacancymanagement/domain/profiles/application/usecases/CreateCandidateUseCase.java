package com.example.vacancymanagement.domain.profiles.application.usecases;

import com.example.vacancymanagement.core.entities.UniqueEntityID;
import com.example.vacancymanagement.core.errors.handling.Either;
import com.example.vacancymanagement.core.errors.handling.Eithers;
import com.example.vacancymanagement.domain.profiles.application.dtos.CandidateRequest;
import com.example.vacancymanagement.domain.profiles.application.dtos.CandidateResponse;
import com.example.vacancymanagement.domain.profiles.application.repositories.CandidateRepository;
import com.example.vacancymanagement.domain.profiles.enterprise.entities.Candidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;

    public Mono<Either<String, CandidateResponse>> execute(CandidateRequest request) {
        Candidate candidate = Candidate.builder()
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .resumeLink(request.resumeLink())
                .areaOfInterest(request.areaOfInterest())
                .build();

        return candidateRepository.save(candidate)
                .map(savedCandidate -> Eithers.<String, CandidateResponse>right(new CandidateResponse(
                        savedCandidate.getName(),
                        savedCandidate.getEmail(),
                        savedCandidate.getPhone(),
                        savedCandidate.getResumeLink(),
                        savedCandidate.getAreaOfInterest())))
                .onErrorResume(e -> Mono.just(Eithers.<String, CandidateResponse>left("Failed to create candidate due to: " + e.getMessage())));
    }
}