package com.example.studentmanagementapplicationn.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    UserDetails loadByUsername(String username) throws UsernameNotFoundException;
}
