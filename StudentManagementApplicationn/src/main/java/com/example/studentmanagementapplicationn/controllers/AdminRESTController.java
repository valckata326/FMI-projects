package com.example.studentmanagementapplicationn.controllers;

import com.example.studentmanagementapplicationn.constants.Constants;
import com.example.studentmanagementapplicationn.entity.ResponseMessage;
import com.example.studentmanagementapplicationn.models.AddCourseModel;
import com.example.studentmanagementapplicationn.models.dto.UserDTO;
import com.example.studentmanagementapplicationn.entity.university.Course;
import com.example.studentmanagementapplicationn.entity.university.Student;
import com.example.studentmanagementapplicationn.entity.university.Teacher;
import com.example.studentmanagementapplicationn.models.CourseModel;
import com.example.studentmanagementapplicationn.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRESTController {
    private final StudentService studentService;
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final RoleAuthorizationService roleAuthorizationService;
    private final AdminService adminService;

    public AdminRESTController(StudentService studentService,
                               CourseService courseService,
                               TeacherService teacherService,
                               RoleAuthorizationService roleAuthorizationService, AdminService adminService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.roleAuthorizationService = roleAuthorizationService;
        this.adminService = adminService;
    }

    @GetMapping("/setTeacher/{teacherId}/{courseId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage> setTeacherToCourse(@PathVariable Long teacherId,
                                                              @PathVariable Long courseId) {
        courseService.setTeacher(teacherId, courseId);
        return ResponseEntity.ok(new ResponseMessage(Constants.SUCCESSFULLY_SET_TEACHER_TO_COURSE));
    }

    @GetMapping("/authorize/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage> authorizeUser(@PathVariable String username) {
        roleAuthorizationService.authorizeUser(username);
        return ResponseEntity.ok(new ResponseMessage(Constants.SUCCESSFULLY_AUTHORIZED_USER));
    }

    @PostMapping("/addCourse")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage> addCourse(@RequestBody AddCourseModel model) {
        courseService.addCourse(model);
        return ResponseEntity.ok(new ResponseMessage(Constants.SUCCESSFULLY_ADDED_COURSE));
    }

    @PostMapping("/addStudent")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage> addStudent(@RequestBody Student student) {
        studentService.add(student);
        return ResponseEntity.ok(new ResponseMessage(Constants.SUCCESSFULLY_ADDED_STUDENT));
    }

    @PostMapping("/addTeacher")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ResponseMessage> addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
        return ResponseEntity.ok(new ResponseMessage(Constants.SUCCESSFULLY_ADDED_TEACHER));
    }

    @GetMapping("/getAllCoursesInformation")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CourseModel>> getAllCoursesWithTheirTeachersAndAverageGrade() {
        List<CourseModel> allCoursesInfo = new ArrayList<>();
        courseService.getAllCourses().forEach((course -> {
            allCoursesInfo.add(new CourseModel(course));
        }));
        return ResponseEntity.ok(allCoursesInfo);
    }

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }
}
