package com.example.vacancymanagement.core.errors.handling;

import lombok.Getter;

@Getter
public class Right<L, R> extends Either<L, R> {
    private final R value;

    public Right(R value) {
        this.value = value;
    }

    @Override
    public boolean isRight() {
        return true;
    }

    @Override
    public boolean isLeft() {
        return false;
    }
}
