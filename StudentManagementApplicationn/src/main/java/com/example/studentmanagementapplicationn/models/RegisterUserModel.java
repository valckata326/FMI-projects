package com.example.studentmanagementapplicationn.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class RegisterUserModel {
    String username;
    String password;
    Set<String> roles;
}
