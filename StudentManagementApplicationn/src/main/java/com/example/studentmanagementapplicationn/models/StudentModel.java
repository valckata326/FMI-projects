package com.example.studentmanagementapplicationn.models;

import com.example.studentmanagementapplicationn.entity.university.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentModel {
    String name;
    String username;
    int age;

    public StudentModel(Student student) {
        this.name = student.getName();
        this.age = student.getAge();
        this.username= student.getUser().getUsername();
    }
}
