package com.sundayuche.usercreation.repository;

import com.sundayuche.usercreation.entity.Role;
import com.sundayuche.usercreation.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
