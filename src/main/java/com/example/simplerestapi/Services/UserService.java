package com.example.simplerestapi.Services;

import com.example.simplerestapi.DTO.CreateUserDTO;
import com.example.simplerestapi.DTO.UserResponseDTO;
import com.example.simplerestapi.Models.Role;
import com.example.simplerestapi.Models.User;
import com.example.simplerestapi.Repositories.RoleRepository;
import com.example.simplerestapi.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public List<UserResponseDTO> getAllUser(){
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserResponseDTO(
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole().getRoleName(),
                        user.getCreated_at()
                )).collect(Collectors.toList());
    }

    public User getUserById(Long id) throws UserPrincipalNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            return user.get();
        } else {
            throw new UserPrincipalNotFoundException("User not found with id: " + id);
        }
    }

    public ResponseEntity<String> createUser(CreateUserDTO createUserDTO){
        User newUser = new User();

        newUser.setUsername(createUserDTO.getUsername());
        newUser.setPassword(createUserDTO.getPassword());
        newUser.setEmail(createUserDTO.getEmail());
        newUser.setAge(createUserDTO.getAge());

        try{
            Role guestRole = roleRepository.findByRole("guest");

            newUser.setRole(guestRole);

            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("User succesfully created.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user." + e.getMessage());
        }
    }
}
