package com.interview.notes.exceptions;

public class AuthorNotFoundException extends ResourceNotFoundException {
    public AuthorNotFoundException(Long id) {
        super(String.format("Author not found with id: %d", id));
    }
}
