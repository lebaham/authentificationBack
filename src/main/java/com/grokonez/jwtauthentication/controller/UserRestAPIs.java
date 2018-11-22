package com.grokonez.jwtauthentication.controller;

import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/user")
public class UserRestAPIs {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUser() {
        Optional<List<User>> listUsers = Optional.ofNullable(userRepository.findAll());
        return ResponseEntity.ok(listUsers);
    }

    @DeleteMapping("delete/{id}")
    public void deleteStudent(@PathVariable long id) {
        userRepository.deleteById(id);
    }

}
