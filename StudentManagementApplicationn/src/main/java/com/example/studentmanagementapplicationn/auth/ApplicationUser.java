package com.example.studentmanagementapplicationn.auth;

import com.example.studentmanagementapplicationn.constants.Constants;
import com.example.studentmanagementapplicationn.entity.users.Role;
import com.example.studentmanagementapplicationn.entity.users.User;
import com.example.studentmanagementapplicationn.exceptions.UserNotFoundException;
import com.example.studentmanagementapplicationn.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ApplicationUser implements UserDetailsService {
    public UserRepository userRepository;

    public ApplicationUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUser = userRepository.findByUsername(username);
        if(foundUser.isEmpty()) {
            throw new UserNotFoundException(Constants.USER_NOT_FOUND_MESSAGE);
        }
        return new org.springframework.security.core.userdetails.User(
                foundUser.get().getUsername(),
                foundUser.get().getPassword(),
                userAuthorities(foundUser.get().getRoles())
        );
    }

    private List<SimpleGrantedAuthority> userAuthorities(Set<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

}
