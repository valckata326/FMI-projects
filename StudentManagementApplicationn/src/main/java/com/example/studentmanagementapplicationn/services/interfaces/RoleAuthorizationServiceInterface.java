package com.example.studentmanagementapplicationn.services.interfaces;

import com.example.studentmanagementapplicationn.entity.users.Type;

import java.util.Set;

public interface RoleAuthorizationServiceInterface {
    void setRoles(String username, Set<Type> roles);

    void authorizeUser(String username);
}
