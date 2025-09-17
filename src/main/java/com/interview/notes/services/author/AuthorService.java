package com.interview.notes.services.author;

import com.interview.notes.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    AuthorDTO getAuthorById(Long id);

    List<AuthorDTO> getAuthors();

    AuthorDTO createAuthor(AuthorDTO authorDTO);
}
