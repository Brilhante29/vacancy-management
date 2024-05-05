package com.example.vacancymanagement.core.errors;

public class NotAllowedError extends RuntimeException implements UseCaseError {
    public NotAllowedError() {
        super("Not allowed");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}