package com.example.vacancymanagement.core.repositories;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CrudRepository<T, ID> {
    Mono<T> save(T entity);
    Mono<PaginatedResult<T>> findAllPaginated(PaginationParams paginationParams);
    Flux<T> findAll();
    Mono<T> findById(ID id);
    Mono<T> update(ID id, T entity);
    Mono<Void> delete(ID id);
}