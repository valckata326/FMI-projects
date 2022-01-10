package com.example.studentmanagementapplicationn.models;

import com.example.studentmanagementapplicationn.entity.university.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourseModel {
    String courseName;
    int totalHours;
    double averageGrade;
    String teacherName;

    public CourseModel(Course course) {
        this.courseName = course.getName();
        this.totalHours = course.getTotalHours();
        if(course.getTeacher() != null) {
            this.teacherName = course.getTeacher().getName();
        }
        else {
            this.teacherName = "Not assigned";
        }
        this.averageGrade = course.getAverageGradeForAllStudents();
    }
}
