package com.example.studentmanagementapplicationn.models;

import com.example.studentmanagementapplicationn.entity.university.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentNameIdModel {
    Long studentId;
    String studentName;

    public StudentNameIdModel(Student student) {
        this.studentId = student.getId();
        this.studentName = student.getName();
    }
}
