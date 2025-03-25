package com.sundayuche.usercreation.service;

import com.sundayuche.usercreation.dto.RegisterRequest;
import com.sundayuche.usercreation.dto.RegisterResponse;
import com.sundayuche.usercreation.dto.UserResponse;
import com.sundayuche.usercreation.entity.Role;
import com.sundayuche.usercreation.entity.RoleType;
import com.sundayuche.usercreation.entity.User;
import com.sundayuche.usercreation.repository.RoleRepository;
import com.sundayuche.usercreation.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder; // Injected encoder

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponse registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role userRole = roleRepository.findByName(RoleType.USER)
                .orElseThrow(() -> new RuntimeException("Default role USER not found"));

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return new RegisterResponse("User registered successfully", user.getEmail(), "USER");
    }

    @Transactional
    public RegisterResponse registerAdmin(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use");
        }

        User admin = new User();
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setEmail(request.getEmail());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));

        Role adminRole = roleRepository.findByName(RoleType.ADMIN)
                .orElseThrow(() -> new RuntimeException("Admin role ADMIN not found"));

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        admin.setRoles(roles);

        userRepository.save(admin);

        return new RegisterResponse("Admin registered successfully", admin.getEmail(), "ADMIN");
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRoles() // Convert Set<Role> to List<String> inside DTO
                ))
                .collect(Collectors.toList());
    }

    public List<UserResponse> getAdminUsers() {

        List<User> adminUsers = userRepository.findByRoles_Name(RoleType.ADMIN);
        return adminUsers.stream()
                .map(user -> new UserResponse(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRoles() // Convert Set<Role> to List<String> inside DTO
                ))
                .collect(Collectors.toList());
    }
}

