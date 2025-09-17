package com.interview.notes.services.notes;

import com.interview.notes.dto.NoteDTO;
import com.interview.notes.model.Author;
import com.interview.notes.model.Note;
import com.interview.notes.repositories.AuthorRepository;
import com.interview.notes.repositories.NotesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServiceImpl implements NotesService {

    private final NotesRepository notesRepository;
    private final AuthorRepository authorRepository;

    public NotesServiceImpl(NotesRepository notesRepository, AuthorRepository authorRepository) {
        this.notesRepository = notesRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public NoteDTO getNoteById(Long id) {
        return notesRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return notesRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public NoteDTO createNote(NoteDTO noteDTO) {

        Author author = authorRepository.findById(noteDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author Not Found"));

        Note note = new Note();
        note.setAuthor(author);
        mapToEntity(note, noteDTO);

        return mapToDTO(notesRepository.save(note));
    }

    @Override
    public void deleteNoteById(Long id) {
        if (!notesRepository.existsById(id)) {
            throw new RuntimeException("Note not found");
        }
        notesRepository.deleteById(id);
    }

    private void mapToEntity(Note note, NoteDTO noteDTO) {
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
    }

    private NoteDTO mapToDTO(Note note) {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId(note.getId());
        noteDTO.setTitle(note.getTitle());
        noteDTO.setContent(note.getContent());
        noteDTO.setAuthorId(note.getAuthor().getId());
        return noteDTO;
    }
}
