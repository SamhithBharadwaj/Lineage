package com.bharadwaj.samhith.lineage.exceptions;

public class InvalidDataException extends RuntimeException{
    String message;

    public InvalidDataException(String message) {
        super(message);
        this.message = message;
    }
}
