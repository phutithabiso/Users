
package com.example.Users.Controller;

import com.example.Users.DTOs.UserRequest;
import com.example.Users.DTOs.UserResponse;
import com.example.Users.Services.UserServices;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    // CREATE USER
    @PostMapping("/register")
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userServices.createUsers(request);
    }

    // GET ALL USERS
    @GetMapping("/all")
    public List<UserResponse> getAllUsers() {
        return userServices.getAllUsers();
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userServices.GetUserById(id);
    }

    // UPDATE USER
    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody UserRequest request) {

        return userServices.updateUsers(request, id);
    }

    // DELETE USER
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userServices.DeleteUsers(id);
    }
}
