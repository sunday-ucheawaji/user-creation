package com.sundayuche.usercreation.controller;

import com.sundayuche.usercreation.dto.RegisterRequest;
import com.sundayuche.usercreation.dto.RegisterResponse;
import com.sundayuche.usercreation.entity.User;
import com.sundayuche.usercreation.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest request) {
        RegisterResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register-admin")
    public ResponseEntity<RegisterResponse> registerAdmin(@RequestBody RegisterRequest request) {
        RegisterResponse response = userService.registerAdmin(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<String> userEmails = users.stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userEmails);
    }


    @GetMapping("/admins")
    public ResponseEntity<List<String>> getAdminUsers() {
        List<User> admins = userService.getAdminUsers();
        List<String> adminEmails = admins.stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
        return ResponseEntity.ok(adminEmails);
    }
}
