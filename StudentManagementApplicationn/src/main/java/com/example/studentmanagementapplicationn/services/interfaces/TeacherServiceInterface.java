package com.example.studentmanagementapplicationn.services.interfaces;

import com.example.studentmanagementapplicationn.models.dto.CoursePlusTeacherModel;
import com.example.studentmanagementapplicationn.entity.university.Teacher;
import com.example.studentmanagementapplicationn.models.CourseNameAverageGradeModel;
import com.example.studentmanagementapplicationn.models.TeacherNameIdModel;

import java.util.List;
import java.util.Set;

public interface TeacherServiceInterface {
    void addTeacher(Teacher teacher);

    List<CourseNameAverageGradeModel> getAllCoursesByTeacher(Long id);

    List<CoursePlusTeacherModel> getAllCoursesWithTheirTeachers();

    Set<TeacherNameIdModel> getAllTeachers();
}
