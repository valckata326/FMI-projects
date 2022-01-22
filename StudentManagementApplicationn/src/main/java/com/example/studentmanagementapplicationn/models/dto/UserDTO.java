package com.example.studentmanagementapplicationn.models.dto;

import com.example.studentmanagementapplicationn.entity.users.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    String username;
    String role = "";

    public UserDTO(User user) {
        this.username = user.getUsername();
        setRoles(user);
    }

    private void setRoles(User user) {
        user.getRoles().forEach(currentRole -> {
            role += currentRole.getName() + " ";
        });
    }
}
