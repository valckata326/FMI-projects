package com.example.studentmanagementapplicationn.models;

import com.example.studentmanagementapplicationn.entity.university.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseNameIDModel {
    private Long courseId;
    private String courseName;

    public CourseNameIDModel(Course course) {
        this.courseId = course.getId();
        this.courseName = course.getName();
    }
}
