package sk.cybersoft.cybernotes.cybernotesspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.cybersoft.cybernotes.cybernotesspring.entity.AccountEntity;
import sk.cybersoft.cybernotes.cybernotesspring.exception.ResourceNotFoundException;
import sk.cybersoft.cybernotes.cybernotesspring.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<AccountEntity>> findAll() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountEntity> findById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        return new ResponseEntity<>(userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found!")), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountEntity> createUser(@Valid @RequestBody AccountEntity user) {
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountEntity> updateUser(@PathVariable(value = "id") Long userId,
                                                    @Valid @RequestBody AccountEntity user) throws ResourceNotFoundException {
        AccountEntity userToUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found!"));

        if(user.getUsername() != null && !user.getUsername().trim().isEmpty()) {
            userToUpdate.setUsername(user.getUsername());
        }
        if(user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
            userToUpdate.setPassword(user.getPassword());
        }

        return new ResponseEntity<>(userRepository.save(userToUpdate), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AccountEntity> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {

        AccountEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found!"));
        userRepository.delete(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
