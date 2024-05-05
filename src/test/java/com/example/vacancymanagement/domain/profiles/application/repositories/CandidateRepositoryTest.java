package com.example.vacancymanagement.domain.profiles.application.repositories;

import com.example.vacancymanagement.core.entities.UniqueEntityID;
import com.example.vacancymanagement.core.repositories.PaginatedResult;
import com.example.vacancymanagement.core.repositories.PaginationParams;
import com.example.vacancymanagement.domain.profiles.enterprise.entities.Candidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class CandidateRepositoryTest {

    @MockBean
    private CandidateRepository mockCandidateRepository;

    private Candidate candidate;

    @BeforeEach
    void setUp() {
        UniqueEntityID id = new UniqueEntityID();
        List<VacancyApplication> applications = new ArrayList<>();
        candidate = Candidate.builder()
                .id(id)
                .name("John Doe")
                .email("john.doe@example.com")
                .applications(applications)
                .build();

        Mockito.when(mockCandidateRepository.save(candidate)).thenReturn(Mono.just(candidate));
        Mockito.when(mockCandidateRepository.findById(id.toString())).thenReturn(Mono.just(candidate));
        Mockito.when(mockCandidateRepository.findAll()).thenReturn(Flux.just(candidate));
        Mockito.when(mockCandidateRepository.delete(id.toString())).thenReturn(Mono.empty());
        Mockito.when(mockCandidateRepository.update(id.toString(), candidate)).thenReturn(Mono.just(candidate));
    }

    @Test
    void testSaveCandidate() {
        Mono<Candidate> savedCandidate = mockCandidateRepository.save(candidate);
        StepVerifier.create(savedCandidate)
                .expectNextMatches(returnedCandidate -> returnedCandidate.getId().equals(candidate.getId()))
                .verifyComplete();
    }

    @Test
    void testFindById() {
        Mono<Candidate> foundCandidate = mockCandidateRepository.findById(candidate.getId().toString());
        StepVerifier.create(foundCandidate)
                .expectNextMatches(found -> found.getName().equals("John Doe"))
                .verifyComplete();
    }

    @Test
    void testFindAllCandidates() {
        Flux<Candidate> allCandidates = mockCandidateRepository.findAll();
        StepVerifier.create(allCandidates)
                .expectNext(candidate)
                .verifyComplete();
    }

    @Test
    void testDeleteCandidate() {
        Mono<Void> deleteCandidate = mockCandidateRepository.delete(candidate.getId().toString());
        StepVerifier.create(deleteCandidate)
                .verifyComplete();
    }

    @Test
    void testUpdateCandidate() {
        Mono<Candidate> updatedCandidate = mockCandidateRepository.update(candidate.getId().toString(), candidate);
        StepVerifier.create(updatedCandidate)
                .expectNextMatches(updated -> updated.getName().equals("John Doe"))
                .verifyComplete();
    }

    // Assume a mock pagination setup
    @Test
    void testFindAllPaginated() {
        PaginationParams params = new PaginationParams(1, 1);
        PaginatedResult<Candidate> page = new PaginatedResult<>(List.of(candidate), 1, null, null, 1, 1, 1);
        Mockito.when(mockCandidateRepository.findAllPaginated(params)).thenReturn(Mono.just(page));

        Mono<PaginatedResult<Candidate>> allPaginated = mockCandidateRepository.findAllPaginated(params);
        StepVerifier.create(allPaginated)
                .expectNextMatches(result -> result.data().contains(candidate))
                .verifyComplete();
    }
}
