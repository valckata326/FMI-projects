package com.example.studentmanagementapplicationn.services;

import com.example.studentmanagementapplicationn.models.dto.UserDTO;
import com.example.studentmanagementapplicationn.entity.users.User;
import com.example.studentmanagementapplicationn.repositories.UserRepository;
import com.example.studentmanagementapplicationn.services.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService implements AdminServiceInterface {
    private final UserRepository userRepository;

    @Autowired
    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        final List<User> allUsers = userRepository.findAll();
        List<UserDTO> result = new ArrayList<>();
        allUsers.forEach(user -> {
            UserDTO userDTO = new UserDTO(user);
            result.add(userDTO);
        });
        return result;
    }
}
