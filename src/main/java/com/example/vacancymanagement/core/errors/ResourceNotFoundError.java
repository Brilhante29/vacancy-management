package com.example.vacancymanagement.core.errors;

public class ResourceNotFoundError extends RuntimeException implements UseCaseError {
    public ResourceNotFoundError() {
        super("Resource not found");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}