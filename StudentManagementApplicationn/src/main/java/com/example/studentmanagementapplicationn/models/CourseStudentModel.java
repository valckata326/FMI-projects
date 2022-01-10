package com.example.studentmanagementapplicationn.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CourseStudentModel {
    String courseName;
    List<StudentGradeModel> studentList;
    Double averageGrade;
}
