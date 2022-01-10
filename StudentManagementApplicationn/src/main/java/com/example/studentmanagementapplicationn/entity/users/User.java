package com.example.studentmanagementapplicationn.entity.users;

import com.example.studentmanagementapplicationn.entity.base.BasicEntity;
import com.example.studentmanagementapplicationn.entity.university.Student;
import com.example.studentmanagementapplicationn.entity.university.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users",
       uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
               @UniqueConstraint(columnNames = "password")
       })
@AllArgsConstructor
public class User extends BasicEntity {
    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Teacher teacher;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Student student;

    public User() {
        roles = new HashSet<>();
    }
}
