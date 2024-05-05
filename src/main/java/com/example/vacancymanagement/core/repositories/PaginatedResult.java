package com.example.vacancymanagement.core.repositories;

import java.util.List;

public record PaginatedResult<T>(
        List<T> data,
        int currentPage,
        Integer previousPage,
        Integer nextPage,
        long totalItems,
        int totalPages,
        int itemsPerPage
) {
}