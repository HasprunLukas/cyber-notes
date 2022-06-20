package sk.cybersoft.cybernotes.cybernotesspring.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sk.cybersoft.cybernotes.cybernotesspring.CyberNotesSpringApplication;
import sk.cybersoft.cybernotes.cybernotesspring.entity.AccountEntity;
import sk.cybersoft.cybernotes.cybernotesspring.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CyberNotesSpringApplication.class)
@Sql(scripts = {"/schema-test.sql", "/data-test.sql"})
public class AccountControllerTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void getAllTest() {
        List<AccountEntity> accounts = accountRepository.findAll();
        Assertions.assertEquals(3, accounts.size(), "Expected account size should be 3!");
    }

    @Test
    public void getByIdTest() {
        Optional<AccountEntity> account = accountRepository.findById(5L);
        Assertions.assertTrue(account.isPresent(), "Account with id 0 should exist!");
        Assertions.assertEquals("0", account.get().getId().toString(), "Id should be 0!");
        Assertions.assertEquals("admin", account.get().getUsername(), "Username should be 'admin'!");
        Assertions.assertEquals("admin", account.get().getPassword(), "Password should be 'admin'!");
        Assertions.assertEquals("2", String.valueOf(account.get().getNotes().size()), "Account with id 0 should have 2 notes!");
    }
}
