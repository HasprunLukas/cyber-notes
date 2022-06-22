package sk.cybersoft.cybernotes.cybernotesspring.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sk.cybersoft.cybernotes.cybernotesspring.CyberNotesSpringApplication;
import sk.cybersoft.cybernotes.cybernotesspring.entity.NoteEntity;
import sk.cybersoft.cybernotes.cybernotesspring.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CyberNotesSpringApplication.class)
@Sql(scripts = {"/schema-test.sql", "/data-test.sql"})
public class NoteControllerTest {
    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void getAllTest() {
        List<NoteEntity> notes = noteRepository.findAll();
        Assertions.assertEquals(3, notes.size(), "Expected note size should be 3!");
    }

    @Test
    public void getByIdTest() {
        Long noteId = 1L;
        Optional<NoteEntity> note = noteRepository.findById(noteId);
        Assertions.assertTrue(note.isPresent(), "Note with id 1 should exist!");
        Assertions.assertEquals("1", note.get().getId().toString(), "Id should be 0!");
        Assertions.assertEquals("nazov1", note.get().getTitle(), "Title should be 'nazov1'!");
        Assertions.assertEquals("text1", note.get().getText(), "Text should be 'text1'!");
    }
}
