package sk.cybersoft.cybernotes.cybernotesspring.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sk.cybersoft.cybernotes.cybernotesspring.CyberNotesSpringApplication;
import sk.cybersoft.cybernotes.cybernotesspring.entity.AccountEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CyberNotesSpringApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/schema-test.sql", "/data-test.sql"})
public class AccountControllerTest {
    @Autowired
    TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    private final String url = "http://localhost:";
    @Test
    public void getAllAccountsTest() {
        ResponseEntity<AccountEntity[]> listResponseEntity = restTemplate.exchange(url + "" + port + "/account", HttpMethod.GET, null, AccountEntity[].class);
        List<AccountEntity> accountEntities = Arrays.asList(Objects.requireNonNull(listResponseEntity.getBody()));
        Assertions.assertEquals(3, accountEntities.size(), "There should be 3 accounts!");
    }

    @Test
    public void getAccountByIdTest() {
        int accountId = 1;
        ResponseEntity<AccountEntity> responseEntity = restTemplate.exchange(url + "" + port + "/account/" + accountId, HttpMethod.GET, null, AccountEntity.class);
        if(responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            responseEntity = null;
        }
        Assertions.assertNotNull(responseEntity, "Response entity should not be null!");
        AccountEntity accountEntity = responseEntity.getBody();
        Assertions.assertNotNull(accountEntity, "Account entity should not be null!");
        Assertions.assertEquals("admin", accountEntity.getUsername(), "Username should be 'admin'!");
        Assertions.assertEquals("admin", accountEntity.getPassword(), "Password should be 'admin'!");
        Assertions.assertEquals(2, accountEntity.getNotes().size(), "Account should have 2 notes!");
    }

    @Test
    public void createNewAccountTest() {
        AccountEntity account = new AccountEntity();
        account.setUsername("testNew");
        account.setPassword("testNew");

        HttpEntity<AccountEntity> request = new HttpEntity<>(account, null);

        ResponseEntity<AccountEntity> responseEntity = restTemplate.exchange(url + "" + port + "/account", HttpMethod.POST, request, AccountEntity.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updateAccountTest() {
        int accountId = 2;
        AccountEntity account = new AccountEntity();
        account.setUsername("testNew");

        HttpEntity<AccountEntity> request = new HttpEntity<>(account, null);

        ResponseEntity<AccountEntity> responseEntity = restTemplate.exchange(url + "" + port + "/account/" + accountId, HttpMethod.PUT, request, AccountEntity.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("testNew", Objects.requireNonNull(responseEntity.getBody()).getUsername(), "Username should be 'testNew'!");
    }

    @Test
    public void deleteAccountTest() {
        int accountId = 1;
        ResponseEntity<String> responseEntity = restTemplate.exchange(url + "" + port + "/account/" + accountId, HttpMethod.DELETE, null, String.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}