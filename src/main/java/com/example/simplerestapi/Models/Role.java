package com.example.simplerestapi.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false, length = 20)
    private String role;

    public String getRoleName(){
        return role;
    }
}
