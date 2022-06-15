package sk.cybersoft.cybernotes.cybernotesspring.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sk.cybersoft.cybernotes.cybernotesspring.entity.AccountEntity;
import sk.cybersoft.cybernotes.cybernotesspring.repository.AccountRepository;

import java.util.Optional;
//TODO all
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountControllerTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findById() {
        Optional<AccountEntity> account = accountRepository.findById(0L);
        Assertions.assertEquals("admin", account.get().getUsername(), "Username should be admin!");
    }
}
