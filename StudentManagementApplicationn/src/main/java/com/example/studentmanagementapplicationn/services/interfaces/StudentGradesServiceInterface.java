package com.example.studentmanagementapplicationn.services.interfaces;

import com.example.studentmanagementapplicationn.entity.university.Course;

import java.util.List;

public interface StudentGradesServiceInterface {
    double averageGradeInCourse(Long courseId);

    double averageStudentGradeForAllCourses(Long studentId);

    void addStudentGradeToCourse(Long studentId, Long courseId, double grade);

    List<Course> getStudentsSorted();
}
