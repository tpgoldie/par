package com.tpg.par.service;

import java.util.Optional;

public class Success<T> extends Outcome<T> {
    public Success(Optional<T> value, String message) {
        super(value, message);
    }
}
