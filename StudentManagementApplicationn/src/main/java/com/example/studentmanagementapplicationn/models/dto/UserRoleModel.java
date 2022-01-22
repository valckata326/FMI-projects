package com.example.studentmanagementapplicationn.models.dto;

import com.example.studentmanagementapplicationn.entity.users.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleModel {
    private String username;
    private Set<Type> roles;
}
