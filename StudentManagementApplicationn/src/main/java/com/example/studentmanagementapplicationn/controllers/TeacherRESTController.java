package com.example.studentmanagementapplicationn.controllers;

import com.example.studentmanagementapplicationn.models.dto.CoursePlusTeacherModel;
import com.example.studentmanagementapplicationn.models.*;
import com.example.studentmanagementapplicationn.services.CourseService;
import com.example.studentmanagementapplicationn.services.StudentGradesService;
import com.example.studentmanagementapplicationn.services.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/teacher")
public class TeacherRESTController {
    private StudentGradesService studentGradesService;
    private CourseService courseService;
    private TeacherService teacherService;

    public TeacherRESTController(StudentGradesService studentGradesService,
                                 CourseService courseService, TeacherService teacherService) {
        this.studentGradesService = studentGradesService;
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping("/getAllCourses/{teacherId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<List<CourseNameAverageGradeModel>> getAllCoursesForTeacher(@PathVariable(name = "teacherId") Long id) {
        if (teacherService.getTeacherById(id) != null) {
            return ResponseEntity.ok(teacherService.getAllCoursesByTeacher(id));
        }
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/getAllCoursesWithTheirTeacher")
    @PreAuthorize("hasAnyAuthority('TEACHER', 'STUDENT', 'ADMIN')")
    public ResponseEntity<List<CoursePlusTeacherModel>> getAllCoursesWithTheirTeachers() {
        return ResponseEntity.ok(teacherService.getAllCoursesWithTheirTeachers());
    }

    @GetMapping("/getAllTeachers")
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    public ResponseEntity<Set<TeacherNameIdModel>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }
}
