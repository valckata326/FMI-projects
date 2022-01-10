package com.example.studentmanagementapplicationn.models;

import com.example.studentmanagementapplicationn.entity.university.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourseNameAverageGradeModel {
    String courseName;
    double averageGrade;

    public CourseNameAverageGradeModel(Course course) {
        this.courseName = course.getName();
        this.averageGrade = course.getAverageGradeForAllStudents();
    }
}
