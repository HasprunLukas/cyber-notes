package sk.cybersoft.cybernotes.cybernotesspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.cybersoft.cybernotes.cybernotesspring.entity.NoteEntity;
import sk.cybersoft.cybernotes.cybernotesspring.entity.UserEntity;
import sk.cybersoft.cybernotes.cybernotesspring.exception.ResourceNotFoundException;
import sk.cybersoft.cybernotes.cybernotesspring.repository.NoteRepository;
import sk.cybersoft.cybernotes.cybernotesspring.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        return new ResponseEntity<>(userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found!")), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity user) {
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable(value = "id") Long userId,
                                                 @Valid @RequestBody UserEntity user) throws ResourceNotFoundException {
        UserEntity userToUpdate = userRepository.findById(userId)
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
    public ResponseEntity<UserEntity> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found!"));
        userRepository.delete(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
