package dev.skirtty.webmessaging.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message); // That's the super constructor
    }
}
