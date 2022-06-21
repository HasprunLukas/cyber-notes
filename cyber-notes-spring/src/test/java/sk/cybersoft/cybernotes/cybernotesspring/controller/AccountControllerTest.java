package sk.cybersoft.cybernotes.cybernotesspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import sk.cybersoft.cybernotes.cybernotesspring.CyberNotesSpringApplication;
import sk.cybersoft.cybernotes.cybernotesspring.entity.AccountEntity;
import sk.cybersoft.cybernotes.cybernotesspring.repository.AccountRepository;
import sk.cybersoft.cybernotes.cybernotesspring.service.AccountService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CyberNotesSpringApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/schema-test.sql", "/data-test.sql"})
public class AccountControllerTest {
    @Autowired
    private AccountController accountController;
    @Autowired
    TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @Test
    public void getAllTest() throws Exception {
        ResponseEntity<AccountEntity[]> listResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/account", AccountEntity[].class);
        List<AccountEntity> accountEntities = Arrays.asList(Objects.requireNonNull(listResponseEntity.getBody()));
    }

//    @Test
//    public void getByIdTest() {
//        Optional<AccountEntity> account = accountRepository.findById(0L);
//        Assertions.assertTrue(account.isPresent(), "Account with id 0 should exist!");
//        Assertions.assertEquals("0", account.get().getId().toString(), "Id should be 0!");
//        Assertions.assertEquals("admin", account.get().getUsername(), "Username should be 'admin'!");
//        Assertions.assertEquals("admin", account.get().getPassword(), "Password should be 'admin'!");
//        Assertions.assertEquals("2", String.valueOf(account.get().getNotes().size()), "Account with id 0 should have 2 notes!");
//    }
}