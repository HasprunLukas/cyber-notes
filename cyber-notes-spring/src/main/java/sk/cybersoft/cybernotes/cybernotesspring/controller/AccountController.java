package sk.cybersoft.cybernotes.cybernotesspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.cybersoft.cybernotes.cybernotesspring.entity.AccountEntity;
import sk.cybersoft.cybernotes.cybernotesspring.exception.AlreadyExistsException;
import sk.cybersoft.cybernotes.cybernotesspring.exception.ResourceNotFoundException;
import sk.cybersoft.cybernotes.cybernotesspring.service.AccountService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountEntity>> findAll() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountEntity> findById(@PathVariable(value = "id") Long accountId) throws ResourceNotFoundException {
        return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountEntity> createUser(@Valid @RequestBody AccountEntity account) throws AlreadyExistsException {
        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountEntity> updateUser(@PathVariable(value = "id") Long accountId,
                                                    @Valid @RequestBody AccountEntity account) throws ResourceNotFoundException {
        return new ResponseEntity<>(accountService.updateAccount(account, accountId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        accountService.deleteAccount(userId);
        return new ResponseEntity<>("User with id: '" + userId + "' successfully deleted!", HttpStatus.OK);
    }
}
