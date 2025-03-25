package com.sundayuche.usercreation.repository;

import com.sundayuche.usercreation.entity.RoleType;
import com.sundayuche.usercreation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Page<User> findAll(Pageable pageable);  // Paginated all users
    Page<User> findByRoles_Name(RoleType roleName, Pageable pageable);
}
