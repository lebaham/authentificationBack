package com.grokonez.jwtauthentication.controller;

import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/user")
public class UserRestAPIs {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUser() {
        Optional<List<User>> listUsers = Optional.ofNullable(userRepository.findAll());
        return ResponseEntity.ok(listUsers);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        Optional<User> user = userRepository.findById(id);

        if(user == null) {
            return  ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return  ResponseEntity.ok().build();
    }


    @PutMapping("update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@Valid @RequestBody  User user) {
         Optional<User> userOne = userRepository.findById(id);

        if(userOne == null) {
            return   ResponseEntity.notFound().build();
        }
        userOne.get().setName(user.getName());
        userOne.get().setUsername(user.getUsername());
        User userResponse =  userRepository.save(userOne.get());
        return  ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
         Optional<User> userOne  = userRepository.findById(id);
         if(userOne == null){
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(userOne);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<User>> showPage(@PathVariable int page){
        return ResponseEntity.ok(userRepository.findAll(new PageRequest(page, 4)));
    }

}
