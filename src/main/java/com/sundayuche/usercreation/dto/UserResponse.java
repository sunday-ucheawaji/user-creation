package com.sundayuche.usercreation.dto;

import com.sundayuche.usercreation.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;

    public UserResponse(String firstName, String lastName, String email, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles.stream()
                .map(role -> role.getName().name()) // Ensure role.getName() returns an Enum, then convert to String
                .collect(Collectors.toList());
    }
}
