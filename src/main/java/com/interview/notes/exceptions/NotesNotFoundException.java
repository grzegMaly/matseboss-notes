package com.interview.notes.exceptions;

public class NotesNotFoundException extends ResourceNotFoundException {
    public NotesNotFoundException(Long id) {
        super(String.format("Note not found with id: %d", id));
    }
}
