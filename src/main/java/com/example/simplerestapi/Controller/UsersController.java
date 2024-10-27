package com.example.simplerestapi.Controller;

import com.example.simplerestapi.Models.User;
import com.example.simplerestapi.Models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<Object> getUsers(){
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> new Object[]{user.getNickname(), user.getEmail(), user.getCreated_at().toString()}).collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) throws UserPrincipalNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            return user.get();
        } else {
            throw new UserPrincipalNotFoundException("User not found with id: " + id);
        }
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestParam String nickname, @RequestParam String password, @RequestParam String email, @RequestParam Integer age){
        User newUser = new User();
        newUser.setNickname(nickname);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setAge(age);

        try{
            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("User succesfully created.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user.");
        }


    }

}
