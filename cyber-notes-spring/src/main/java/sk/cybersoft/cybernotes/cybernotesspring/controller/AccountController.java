package sk.cybersoft.cybernotes.cybernotesspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.cybersoft.cybernotes.cybernotesspring.entity.AccountEntity;
import sk.cybersoft.cybernotes.cybernotesspring.exception.ResourceNotFoundException;
import sk.cybersoft.cybernotes.cybernotesspring.repository.AccountRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @GetMapping
    public ResponseEntity<List<AccountEntity>> findAll() {
        return new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountEntity> findById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        return new ResponseEntity<>(accountRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found!")), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountEntity> createUser(@Valid @RequestBody AccountEntity user) {
        accountRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountEntity> updateUser(@PathVariable(value = "id") Long userId,
                                                    @Valid @RequestBody AccountEntity user) throws ResourceNotFoundException {
        AccountEntity userToUpdate = accountRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found!"));

        if(user.getUsername() != null && !user.getUsername().trim().isEmpty()) {
            userToUpdate.setUsername(user.getUsername());
        }
        if(user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
            userToUpdate.setPassword(user.getPassword());
        }

        return new ResponseEntity<>(accountRepository.save(userToUpdate), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AccountEntity> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {

        AccountEntity user = accountRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found!"));
        accountRepository.delete(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
