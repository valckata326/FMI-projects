package com.example.studentmanagementapplicationn.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.studentmanagementapplicationn.security.ApplicationUserPermissions.*;

public enum ApplicationUserRole {
    STUDENT(Set.of(SEE_AVERAGE_GRADE,
            SEE_AVERAGE_GRADE_IN_COURSE,
            SEE_COURSE_INFORMATION)),
    TEACHER(Set.of(SEE_AVERAGE_GRADE,
            SEE_AVERAGE_GRADE_IN_COURSE,
            SEE_COURSE_INFORMATION,
            ADD_STUDENT_TO_COURSE,
            ADD_GRADE_TO_STUDENT)),
    ADMIN(Set.of(SEE_AVERAGE_GRADE,
            SEE_AVERAGE_GRADE_IN_COURSE,
            SEE_COURSE_INFORMATION,
            ADD_STUDENT_TO_COURSE,
            ADD_GRADE_TO_STUDENT,
            ADD_TEACHER_TO_COURSE,
            CHANGE_ROLE));

    private final Set<ApplicationUserPermissions> permissions;

    ApplicationUserRole(Set<ApplicationUserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
