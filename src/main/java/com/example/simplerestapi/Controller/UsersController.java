package com.example.simplerestapi.Controller;

import com.example.simplerestapi.DTO.CreateUserDTO;
import com.example.simplerestapi.DTO.UserResponseDTO;
import com.example.simplerestapi.Models.Role;
import com.example.simplerestapi.Repositories.RoleRepository;
import com.example.simplerestapi.Models.User;
import com.example.simplerestapi.Repositories.UserRepository;
import com.example.simplerestapi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsersController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserResponseDTO> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) throws UserPrincipalNotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDTO createUserDTO){
        return userService.createUser(createUserDTO);
    }

}
