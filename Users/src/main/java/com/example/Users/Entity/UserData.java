package com.example.Users.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="Peoples")
@Data
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotBlank(message = "Name is required")
    @Size(min =2, max = 30, message= "Name contain 2 to 30 characters")
    @Column(name = "name", nullable = false)
    private String name;
    @Email(message = " Email is required")
    @Column(nullable = false, unique = true ,name = "email")
    private String email;


}
