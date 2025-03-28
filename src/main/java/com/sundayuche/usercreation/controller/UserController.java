package com.sundayuche.usercreation.controller;

import com.sundayuche.usercreation.dto.RegisterRequest;
import com.sundayuche.usercreation.dto.RegisterResponse;
import com.sundayuche.usercreation.dto.UserResponse;
import com.sundayuche.usercreation.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "Endpoints for user management")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    @Operation(summary = "Register a new user", description = "Registers a new user with basic details")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest request) {
        RegisterResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin-registration")
    @Operation(summary = "Register an admin user", description = "Registers a new admin user")
    public ResponseEntity<RegisterResponse> registerAdmin(@RequestBody RegisterRequest request) {
        RegisterResponse response = userService.registerAdmin(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    @Operation(summary = "Get all users", description = "Fetches a list of all users")
    public ResponseEntity<Page<UserResponse>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        Page<UserResponse> userResponses = userService.getAllUsers(page, size);
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/admins")
    @Operation(summary = "Get all admin users", description = "Fetches a list of all admin users")
    public ResponseEntity<Page<UserResponse>> getAdminUsers(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Page<UserResponse> userResponses = userService.getAdminUsers(page, size);
        return ResponseEntity.ok(userResponses);

    }
}
