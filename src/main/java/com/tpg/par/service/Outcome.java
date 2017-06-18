package com.tpg.par.service;

import java.util.Optional;

public abstract class Outcome<T> {
    private Optional<T> value;
    private String message;

    protected Outcome(Optional<T> value, String message) {
        this.value = value;
        this.message = message;
    }

    public Optional<T> getValue() { return value; }

    public String getMessage() {
        return message;
    }
}
