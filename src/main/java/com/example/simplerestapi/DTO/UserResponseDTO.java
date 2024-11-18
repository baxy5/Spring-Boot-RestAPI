package com.example.simplerestapi.DTO;


import java.time.LocalDate;

public class UserResponseDTO {
    private String username;
    private String email;
    private String roleName;
    private LocalDate createdAt;

    public UserResponseDTO(String username, String email, String roleName, LocalDate createdAt){
        this.username = username;
        this.email = email;
        this.roleName = roleName;
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRole(String roleName) {
        this.roleName = roleName;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
