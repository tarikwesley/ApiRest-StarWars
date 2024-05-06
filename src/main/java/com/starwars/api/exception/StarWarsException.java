package com.starwars.api.exception;

public class StarWarsException extends RuntimeException {
    public StarWarsException(String message) {
        super(message);
    }
}
