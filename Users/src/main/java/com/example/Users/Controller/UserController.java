package com.example.Users.Controller;

import com.example.Users.DTOs.UserRequest;
import com.example.Users.DTOs.UserResponse;
import com.example.Users.Services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }
    @PostMapping
    public UserResponse CreateUser(@RequestBody UserRequest request){
        return  userServices.createUsers(request);
    }

}
