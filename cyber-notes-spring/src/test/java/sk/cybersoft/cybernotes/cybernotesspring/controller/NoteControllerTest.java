package sk.cybersoft.cybernotes.cybernotesspring.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sk.cybersoft.cybernotes.cybernotesspring.entity.NoteEntity;
import sk.cybersoft.cybernotes.cybernotesspring.repository.NoteRepository;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SpringBootTest
public class NoteControllerTest {
    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void findById() {
        Optional<NoteEntity> account = noteRepository.findById(0L);
        Assertions.assertEquals("testing", account.get().getText(), "Username should be admin!");
    }
}
