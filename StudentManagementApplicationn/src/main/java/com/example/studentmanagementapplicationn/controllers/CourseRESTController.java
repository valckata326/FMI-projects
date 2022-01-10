package com.example.studentmanagementapplicationn.controllers;

import com.example.studentmanagementapplicationn.constants.Constants;
import com.example.studentmanagementapplicationn.entity.ResponseMessage;
import com.example.studentmanagementapplicationn.models.*;
import com.example.studentmanagementapplicationn.services.CourseService;
import com.example.studentmanagementapplicationn.services.StudentGradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/course")
public class CourseRESTController {
    private final CourseService courseService;
    private final StudentGradesService studentGradesService;

    @Autowired
    public CourseRESTController(CourseService courseService, StudentGradesService studentGradesService) {
        this.courseService = courseService;
        this.studentGradesService = studentGradesService;
    }

    @GetMapping("/getCoursesWithTheirTeacher")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    public ResponseEntity<List<CourseTeacherModel>> getCoursesNameWithTheirTeacher() {
        return ResponseEntity.ok(courseService.getCoursesNamesWithTheirTeacher());
    }

    @GetMapping("/getCourseWithStudents")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<List<CourseStudentModel>> getCourseWithItsStudents() {
        return ResponseEntity.ok(courseService.getCoursesWithStudents());
    }

    @GetMapping("/getCoursesNamesAndIds")
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    public ResponseEntity<List<CourseNameIDModel>> getCoursesNameAndIds() {
        return ResponseEntity.ok(this.courseService.getAllCoursesNameAndId());
    }

    @GetMapping("/getStudentsAvailableForCourse/{id}")
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    public ResponseEntity<List<StudentNameIdModel>> getAvailableStudentsForCourse(@PathVariable Long id) {
        return ResponseEntity.ok(this.courseService.getAllStudentsAvailableForCourse(id));
    }

    @GetMapping("/addStudentToCourse/{courseId}/{studentId}")
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    public ResponseEntity<ResponseMessage> addStudentToCourse(@PathVariable Long courseId,
                                                              @PathVariable Long studentId) {
        courseService.addStudentToCourse(studentId, courseId);
        return ResponseEntity.ok(new ResponseMessage(Constants.SUCCESSFULLY_ADDED_STUDENT_TO_COURSE));
    }

    @GetMapping("/getAllStudentsFromCourse/{id}")
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    public ResponseEntity<Set<StudentNameIdModel>> getAllStudentsFromCourse(@PathVariable Long id) {
        return ResponseEntity.ok(this.courseService.getAllStudentsFromCourse(id));
    }

    @PostMapping("/addStudentGrade")
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    public ResponseEntity<ResponseMessage> addStudentGradeToCourse(@RequestBody StudentCourseGradeModel model) {
        studentGradesService.addStudentGradeToCourse(model.getStudentId(),
                model.getCourseId(), model.getGrade());
        return ResponseEntity.ok(new ResponseMessage(Constants.SUCCESSFULLY_ADDED_STUDENT_GRADE_TO_COURSE));
    }

    @GetMapping("/getCoursesWithoutTeachers")
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    public ResponseEntity<Set<CourseNameIDModel>> getAllCoursesWithoutTeacher() {
        return ResponseEntity.ok(courseService.getCoursesWithoutTeacher());
    }
}
