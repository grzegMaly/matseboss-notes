package com.interview.notes.services.notes;

import com.interview.notes.dto.NoteDTO;

import java.util.List;

public interface NotesService {
    NoteDTO getNoteById(Long id);

    List<NoteDTO> getAllNotes();

    NoteDTO createNote(NoteDTO noteDTO);

    void deleteNoteById(Long id);
}
