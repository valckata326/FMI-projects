package com.example.studentmanagementapplicationn.services;

import com.example.studentmanagementapplicationn.constants.Constants;
import com.example.studentmanagementapplicationn.entity.users.Role;
import com.example.studentmanagementapplicationn.entity.users.Type;
import com.example.studentmanagementapplicationn.entity.users.User;
import com.example.studentmanagementapplicationn.exceptions.UserNotFoundException;
import com.example.studentmanagementapplicationn.repositories.RoleRepository;
import com.example.studentmanagementapplicationn.repositories.UserRepository;
import com.example.studentmanagementapplicationn.services.interfaces.RoleAuthorizationServiceInterface;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Component
public class RoleAuthorizationService implements RoleAuthorizationServiceInterface {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public RoleAuthorizationService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void setRoles(String username, Set<Type> roles) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        verifyUserNotNull(foundUser);
        foundUser.get().setRoles(createRoles(roles));
        userRepository.save(foundUser.get());
    }

    private void verifyUserNotNull(Optional<User> foundUser) {
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public void authorizeUser(String username) {
        final Optional<User> foundUser = userRepository.findByUsername(username);
        verifyUserNotNull(foundUser);
        foundUser.get().getRoles().clear();
        Set<Type> types = new HashSet<>();
        if(foundUser.get().getStudent() != null) {
            types.add(Type.STUDENT);
        }
        else if(foundUser.get().getTeacher() != null) {
            types.add(Type.TEACHER);
        }
        foundUser.get().setRoles(createRoles(types));
        userRepository.save(foundUser.get());
    }

    private Set<Role> createRoles(Set<Type> roles) {
        Set<Role> returnRoles = new HashSet<>();

        for (Type roleType : roles) {
            Role role = roleRepository.findByName(roleType.name());
            if (role == null) {
                role = roleRepository.save(new Role(roleType));
            }

            returnRoles.add(role);
        }
        return returnRoles;
    }
}
