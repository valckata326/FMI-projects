package com.example.studentmanagementapplicationn.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseUserModel {
    String token;
    String type = "Bearer";
    Long id;
    String username;
    List<String> roles;

    public ResponseUserModel(String token, Long id, String username, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
