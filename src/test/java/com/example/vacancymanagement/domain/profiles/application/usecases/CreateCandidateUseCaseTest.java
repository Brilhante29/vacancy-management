package com.example.vacancymanagement.domain.profiles.application.usecases;

import com.example.vacancymanagement.core.entities.UniqueEntityID;
import com.example.vacancymanagement.core.errors.handling.Either;
import com.example.vacancymanagement.core.errors.handling.Left;
import com.example.vacancymanagement.core.errors.handling.Right;
import com.example.vacancymanagement.domain.profiles.application.dtos.CandidateRequest;
import com.example.vacancymanagement.domain.profiles.application.dtos.CandidateResponse;
import com.example.vacancymanagement.domain.profiles.enterprise.entities.Candidate;
import com.example.vacancymanagement.domain.profiles.application.repositories.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.mockito.Mockito.when;

public class CreateCandidateUseCaseTest {

    @Mock
    private CandidateRepository candidateRepository;

    private CreateCandidateUseCase createCandidateUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        createCandidateUseCase = new CreateCandidateUseCase(candidateRepository);
    }

    @Test
    public void testExecuteSuccess() {
        CandidateRequest request = new CandidateRequest("John Doe", "john.doe@example.com", new CandidateApplications(Collections.emptyList()));
        Candidate candidate = new Candidate(new UniqueEntityID(), request.name(), request.email(), request.applications().getCurrentItems());

        when(candidateRepository.save(candidate)).thenReturn(Mono.just(candidate));

        Mono<Either<String, CandidateResponse>> result = createCandidateUseCase.execute(request);

        StepVerifier.create(result)
                .assertNext(either -> {
                    if (either.isRight()) {
                        CandidateResponse response = ((Right<String, CandidateResponse>) either).getValue();
                        assert response.email().equals("john.doe@example.com");
                    } else {
                        assert false : "Test failed due to unexpected Left";
                    }
                })
                .verifyComplete();
    }

    @Test
    public void testExecuteFailure() {
        CandidateRequest request = new CandidateRequest("John Doe", "john.doe@example.com", new CandidateApplications(Collections.emptyList()));
        Candidate candidate = new Candidate(new UniqueEntityID(), request.name(), request.email(), request.applications().getCurrentItems());

        when(candidateRepository.save(candidate)).thenReturn(Mono.error(new RuntimeException("Database error")));

        Mono<Either<String, CandidateResponse>> result = createCandidateUseCase.execute(request);

        StepVerifier.create(result)
                .assertNext(either -> {
                    if (either.isLeft()) {
                        String errorMessage = ((Left<String, CandidateResponse>) either).getValue();
                        assert errorMessage.contains("Failed to create candidate");
                    } else {
                        assert false : "Test failed due to unexpected Right";
                    }
                })
                .verifyComplete();
    }
}
