package com.interview.notes.controllers;

import com.interview.notes.dto.AuthorDTO;
import com.interview.notes.response.ApiCollectionResponse;
import com.interview.notes.response.ApiResponse;
import com.interview.notes.services.author.AuthorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorDTO>> getAuthorById(@PathVariable @Positive Long id) {

        AuthorDTO authorDTO = authorService.getAuthorById(id);
        ApiResponse<AuthorDTO> response = new ApiResponse<>("OK", authorDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiCollectionResponse<AuthorDTO>> getAuthors() {

        List<AuthorDTO> authors = authorService.getAuthors();
        ApiCollectionResponse<AuthorDTO> response = new ApiCollectionResponse<>("OK", authors);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AuthorDTO>> createAuthor(@RequestBody @Valid AuthorDTO authorDTO) {

        AuthorDTO newAuthor = authorService.createAuthor(authorDTO);
        ApiResponse<AuthorDTO> response = new ApiResponse<>("Successfully Created", newAuthor);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
