package com.example.studentmanagementapplicationn.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserModel {
    String username;
    String password;
    String userType;
}
