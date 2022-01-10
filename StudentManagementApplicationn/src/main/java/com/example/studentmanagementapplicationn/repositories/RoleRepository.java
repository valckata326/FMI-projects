package com.example.studentmanagementapplicationn.repositories;

import com.example.studentmanagementapplicationn.entity.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
