package sk.cybersoft.cybernotes.cybernotesspring.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import sk.cybersoft.cybernotes.cybernotesspring.CyberNotesSpringApplication;
import sk.cybersoft.cybernotes.cybernotesspring.entity.AccountEntity;
import sk.cybersoft.cybernotes.cybernotesspring.repository.AccountRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CyberNotesSpringApplication.class)
@Sql(scripts = {"/schema-test.sql", "/data-test.sql"})
public class AccountControllerTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void getAllTest() {
        List<AccountEntity> accounts = accountRepository.findAll();
        Assert.assertEquals("Expected account size should be 2!", 2, accounts.size(), 0);
    }

//    @Test
//    public void getByIdTest() {
//        AccountEntity account = accountRepository.getById(0L);
//        System.out.println(account);
//    }
}
