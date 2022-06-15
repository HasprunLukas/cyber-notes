package sk.cybersoft.cybernotes.cybernotesspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.cybersoft.cybernotes.cybernotesspring.entity.NoteEntity;
import sk.cybersoft.cybernotes.cybernotesspring.entity.AccountEntity;
import sk.cybersoft.cybernotes.cybernotesspring.exception.ResourceNotFoundException;
import sk.cybersoft.cybernotes.cybernotesspring.repository.NoteRepository;
import sk.cybersoft.cybernotes.cybernotesspring.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<NoteEntity>> findAll() {
        return new ResponseEntity<>(noteRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<NoteEntity> findById(@PathVariable(value = "id") Long noteId) throws ResourceNotFoundException {

        return new ResponseEntity<>(noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note with id: " + noteId + " not found!")), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NoteEntity> createNote(@Valid @RequestBody NoteEntity note) throws ResourceNotFoundException {
        AccountEntity user = userRepository.findById(note.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Note with id: " + note.getUser().getId() + " not found!"));

        note.setUser(user);
        noteRepository.save(note);

        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<NoteEntity> updateNote(@PathVariable(value = "id") Long noteId,
                                                 @Valid @RequestBody NoteEntity note) throws ResourceNotFoundException {
        NoteEntity noteToUpdate = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note with id: " + noteId + " not found!"));

        AccountEntity user = userRepository.findById(note.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + note.getUser().getId() + " not found!"));

        if(note.getTitle() != null && !note.getTitle().trim().isEmpty()) {
            noteToUpdate.setTitle(note.getTitle());
        }
        if(note.getText() != null && !note.getText().trim().isEmpty()) {
            noteToUpdate.setText(note.getText());
        }
        noteToUpdate.setUser(user);

        return new ResponseEntity<>(noteRepository.save(noteToUpdate), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<NoteEntity> deleteNote(@PathVariable(value = "id") Long noteId) throws ResourceNotFoundException {
        NoteEntity note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note with id: " + noteId + " not found!"));

        noteRepository.delete(note);

        return new ResponseEntity<>(note, HttpStatus.OK);
    }
}
