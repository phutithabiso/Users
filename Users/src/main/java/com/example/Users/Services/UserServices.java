
package com.example.Users.Services;

import com.example.Users.DTOs.UserRequest;
import com.example.Users.DTOs.UserResponse;
import com.example.Users.Entity.UserData;
import com.example.Users.Repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServices {

    private final UserRepository repository;

    // CONSTRUCTOR INJECTION
    public UserServices(UserRepository repository) {
        this.repository = repository;
    }

    // CREATE
    @Transactional
    public UserResponse createUsers(UserRequest request) {

        if (repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException(
                    "Email already registered. Please use another email."
            );
        }

        UserData u = new UserData();

        u.setName(request.name());
        u.setEmail(request.email());

        UserData save = repository.save(u);

        return mapToRequest(save);
    }

    // UPDATE
    @Transactional
    public UserResponse updateUsers(UserRequest request, Long id) {

        UserData user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(request.name());
        user.setEmail(request.email());

        UserData saveData = repository.save(user);

        return mapToRequest(saveData);
    }

    // READ ALL
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {

        return repository.findAll()
                .stream()
                .map(this::mapToRequest)
                .toList();
    }

    // READ BY ID
    @Transactional(readOnly = true)
    public UserResponse GetUserById(Long id) {

        UserData user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToRequest(user);
    }

    // DELETE
    @Transactional
    public String DeleteUsers(Long id) {

        UserData user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        repository.delete(user);

        return "User successfully deleted: " + id;
    }

    // MAP ENTITY TO RESPONSE DTO
    private UserResponse mapToRequest(UserData save) {

        return new UserResponse(
                save.getId(),
                save.getName(),
                save.getEmail()
        );
    }
}
