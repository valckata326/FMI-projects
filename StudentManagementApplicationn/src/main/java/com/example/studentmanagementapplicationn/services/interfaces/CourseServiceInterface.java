package com.example.studentmanagementapplicationn.services.interfaces;

import com.example.studentmanagementapplicationn.entity.university.Course;
import com.example.studentmanagementapplicationn.models.*;

import java.util.List;
import java.util.Set;

public interface CourseServiceInterface {
    void addCourse(AddCourseModel addCourseModel);

    void setTeacher(Long teacherId, Long courseId);

    void addStudentToCourse(Long studentId, Long courseId);

    List<Course> getAllCourses();

    List<CourseTeacherModel> getCoursesNamesWithTheirTeacher();

    List<CourseStudentModel> getCoursesWithStudents();

    List<CourseNameIDModel> getAllCoursesNameAndId();

    List<StudentNameIdModel> getAllStudentsAvailableForCourse(Long courseId);

    Set<StudentNameIdModel> getAllStudentsFromCourse(Long courseId);

    Set<CourseNameIDModel> getCoursesWithoutTeacher();
}
