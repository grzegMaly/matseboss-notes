package com.interview.notes.controllers;

import com.interview.notes.dto.NoteDTO;
import com.interview.notes.response.ApiCollectionResponse;
import com.interview.notes.response.ApiResponse;
import com.interview.notes.services.notes.NotesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/notes")
public class NotesController {

    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NoteDTO>> getNoteById(@PathVariable Long id) {

        NoteDTO noteDTO = notesService.getNoteById(id);
        ApiResponse<NoteDTO> response = new ApiResponse<>("OK", noteDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<? extends ApiResponse<?>> getNotes() {

        List<NoteDTO> notes = notesService.getAllNotes();
        ApiCollectionResponse<NoteDTO> response = new ApiCollectionResponse<>("OK", notes);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<NoteDTO>> createNote(@RequestBody @Valid NoteDTO noteDTO) {

        NoteDTO savedNote = notesService.createNote(noteDTO);
        ApiResponse<NoteDTO> response = new ApiResponse<>("Note Created Successfully", savedNote);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id) {
        notesService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }
}
