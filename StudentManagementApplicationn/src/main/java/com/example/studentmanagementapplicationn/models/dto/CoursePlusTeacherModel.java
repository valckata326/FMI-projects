package com.example.studentmanagementapplicationn.models.dto;

import com.example.studentmanagementapplicationn.entity.university.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoursePlusTeacherModel {
    String courseName;
    int hours;
    String teacherName;

    public CoursePlusTeacherModel(Course course) {
        this.courseName = course.getName();
        this.hours = course.getTotalHours();
        if(course.getTeacher() == null) {
            this.teacherName = "Not assigned";
        }
        else {
            this.teacherName = course.getTeacher().getName();
        }
    }
}
