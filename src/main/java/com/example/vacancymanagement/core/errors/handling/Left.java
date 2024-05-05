package com.example.vacancymanagement.core.errors.handling;

import lombok.Getter;

@Getter
public class Left<L, R> extends Either<L, R> {
    private final L value;

    public Left(L value) {
        this.value = value;
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public boolean isRight() {
        return false;
    }
}