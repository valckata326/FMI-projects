package com.example.studentmanagementapplicationn.controllers;

import com.example.studentmanagementapplicationn.entity.university.Course;
import com.example.studentmanagementapplicationn.models.Grade;
import com.example.studentmanagementapplicationn.models.StudentModel;
import com.example.studentmanagementapplicationn.services.CourseService;
import com.example.studentmanagementapplicationn.services.StudentGradesService;
import com.example.studentmanagementapplicationn.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentRESTController {
    private final StudentService studentService;
    private final CourseService courseService;
    private final StudentGradesService studentGradesService;

    @Autowired
    public StudentRESTController(StudentService studentService,
                                 CourseService courseService,
                                 StudentGradesService studentGradesService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.studentGradesService = studentGradesService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentModel> getStudent(@PathVariable(name = "id") Long studentId) {
        return ResponseEntity.ok(studentService.getStudent(studentId));
    }

    @GetMapping("/display_courses")
        @PreAuthorize("hasRole('student')")
    public ResponseEntity<List<Course>> displayAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/averageGradeIn/{id}")
    @PreAuthorize("hasRole('student')")
    public ResponseEntity<Double> averageGradeInCourse(@PathVariable(name = "id") Long courseId) {
        return ResponseEntity.ok(studentGradesService.averageGradeInCourse(courseId));
    }

    @GetMapping("/averageGradeOf/{id}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<Grade> studentAverageGrade(@PathVariable(name = "id") Long studentId) {
        return ResponseEntity.ok(new Grade(studentService.getAverageGradeForAStudent(studentId)));
    }
}
