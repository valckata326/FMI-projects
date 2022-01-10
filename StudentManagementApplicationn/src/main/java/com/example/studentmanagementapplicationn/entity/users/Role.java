package com.example.studentmanagementapplicationn.entity.users;

import com.example.studentmanagementapplicationn.entity.base.BasicEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends BasicEntity {
    @Column(unique = true)
    private String name;

    @ManyToMany
    private Set<User> users;

    public Role() {
        users = new HashSet<>();
    }

    public Role(Type roleType) {
        name = roleType.name();
        users = new HashSet<>();
    }

}
