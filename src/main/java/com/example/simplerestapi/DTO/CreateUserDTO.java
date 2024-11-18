package com.example.simplerestapi.DTO;

import com.example.simplerestapi.Models.Role;
import io.micrometer.common.lang.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

public class CreateUserDTO {
    private String username;
    private String password;
    private String email;
    private Integer age;

    public CreateUserDTO(String username, String password, String email, Integer age){
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }
}
