package it.epicode.d5w7.exceptions;

public class SoldOutException extends RuntimeException {
    public SoldOutException(String message) {
        super(message);
    }
}
