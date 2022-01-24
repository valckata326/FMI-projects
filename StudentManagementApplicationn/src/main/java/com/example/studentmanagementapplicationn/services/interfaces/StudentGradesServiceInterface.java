package com.example.studentmanagementapplicationn.services.interfaces;

import com.example.studentmanagementapplicationn.entity.university.Course;
import com.example.studentmanagementapplicationn.models.CourseNameAverageGradeModel;

import java.util.List;

public interface StudentGradesServiceInterface {
    double averageGradeInCourse(Long courseId);

    double averageStudentGradeForAllCourses(Long studentId);

    void addStudentGradeToCourse(Long studentId, Long courseId, double grade);

    List<CourseNameAverageGradeModel> getAllCoursesWithAverageGradeForStudent(Long studentId);

    List<Course> getStudentsSorted();
}
