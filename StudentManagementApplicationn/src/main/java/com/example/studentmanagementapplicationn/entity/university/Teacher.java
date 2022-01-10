package com.example.studentmanagementapplicationn.entity.university;

import com.example.studentmanagementapplicationn.constants.Constants;
import com.example.studentmanagementapplicationn.entity.users.User;
import com.example.studentmanagementapplicationn.entity.base.Degree;
import com.example.studentmanagementapplicationn.entity.base.NamedEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table
@Setter
public class Teacher extends NamedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;


    @OneToOne
    @MapsId
    @JoinColumn
    private User user;

    public Teacher() {
        courses = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return getId().equals(teacher.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(Constants.NAME_QUOTES).append(getName()).append(System.lineSeparator());
        builder.append(Constants.DEGREE_QUOTE).append(getDegree().getName()).append(System.lineSeparator());
        return builder.toString();
    }
}
