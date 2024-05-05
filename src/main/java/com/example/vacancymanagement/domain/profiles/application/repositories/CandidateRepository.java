package com.example.vacancymanagement.domain.profiles.application.repositories;

import com.example.vacancymanagement.core.entities.UniqueEntityID;
import com.example.vacancymanagement.core.repositories.CrudRepository;
import com.example.vacancymanagement.domain.profiles.enterprise.entities.Candidate;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidateRepository extends CrudRepository<Candidate, String> {}
