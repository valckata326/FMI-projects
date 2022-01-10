package com.example.studentmanagementapplicationn.controllers;

import com.example.studentmanagementapplicationn.constants.Constants;
import com.example.studentmanagementapplicationn.entity.ResponseMessage;
import com.example.studentmanagementapplicationn.entity.dto.UserRoleModel;
import com.example.studentmanagementapplicationn.entity.university.Course;
import com.example.studentmanagementapplicationn.entity.university.Student;
import com.example.studentmanagementapplicationn.entity.university.Teacher;
import com.example.studentmanagementapplicationn.models.CourseModel;
import com.example.studentmanagementapplicationn.models.TeacherToCourseModel;
import com.example.studentmanagementapplicationn.services.CourseService;
import com.example.studentmanagementapplicationn.services.RoleAuthorizationService;
import com.example.studentmanagementapplicationn.services.StudentService;
import com.example.studentmanagementapplicationn.services.TeacherService;
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

    public AdminRESTController(StudentService studentService,
                               CourseService courseService,
                               TeacherService teacherService,
                               RoleAuthorizationService roleAuthorizationService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.roleAuthorizationService = roleAuthorizationService;
    }

    @GetMapping("/setTeacher/{teacherId}/{courseId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage> setTeacherToCourse(@PathVariable Long teacherId,
                                                              @PathVariable Long courseId) {
        courseService.setTeacher(teacherId, courseId);
        return ResponseEntity.ok(new ResponseMessage(Constants.SUCCESSFULLY_SET_TEACHER_TO_COURSE));
    }

    @PostMapping("/setRoles")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ResponseMessage> changeRoles(@RequestBody UserRoleModel model) {
        roleAuthorizationService.setRoles(model.getUsername(), model.getRoles());
        return ResponseEntity.ok(new ResponseMessage(Constants.SUCCESSFULLY_CHANGED_ROLES_TO_THAT_USER));
    }

    @PostMapping("/addCourse")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ResponseMessage> addCourse(@RequestBody Course model) {
        courseService.add(model);
        return ResponseEntity.ok(new ResponseMessage(Constants.SUCCESSFULLY_ADDED_COURSE));
    }

    @PostMapping("/addStudent")
    @PreAuthorize("hasRole('admin')")
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
}
