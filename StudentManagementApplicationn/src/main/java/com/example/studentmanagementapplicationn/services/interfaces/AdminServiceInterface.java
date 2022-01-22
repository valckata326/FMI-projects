package com.example.studentmanagementapplicationn.services.interfaces;

import com.example.studentmanagementapplicationn.models.dto.UserDTO;

import java.util.List;

public interface AdminServiceInterface {
    List<UserDTO> getAllUsers();
}
