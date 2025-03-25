package com.sundayuche.usercreation.repository;

import com.sundayuche.usercreation.entity.RoleType;
import com.sundayuche.usercreation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRoles_Name(RoleType roleName);
}
