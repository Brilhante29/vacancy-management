package com.example.vacancymanagement.core.errors.handling;

public abstract class Either<L, R> {
    public abstract boolean isLeft();
    public abstract boolean isRight();
}
