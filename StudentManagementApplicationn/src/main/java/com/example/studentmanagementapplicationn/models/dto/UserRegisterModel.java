package com.example.studentmanagementapplicationn.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserRegisterModel {
    private String username;
    private String password;
    private String name;
}
