package com.interview.notes.services.author;

import com.interview.notes.dto.AuthorDTO;
import com.interview.notes.model.Author;
import com.interview.notes.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {


    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        return authorRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new EntityNotFoundException("User Not found"));
    }

    @Override
    public List<AuthorDTO> getAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = mapToEntity(authorDTO);
        return mapToDTO(authorRepository.save(author));
    }

    private AuthorDTO mapToDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getName());
    }

    private Author mapToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        return author;
    }
}
