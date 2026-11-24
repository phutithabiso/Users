package com.example.Users.DTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Name is required")
        @Size(min =2, max = 30, message= "Name contain 2 to 30 characters")
        @Column(name = "name", nullable = false)
        String name,
        @Email(message = " Email is required")
        @Column(nullable = false, unique = true ,name = "email")
        String email
) {
}
