package com.example.studentmanagementapplicationn.controllers;

import com.example.studentmanagementapplicationn.constants.Constants;
import com.example.studentmanagementapplicationn.entity.ResponseMessage;
import com.example.studentmanagementapplicationn.entity.base.Degree;
import com.example.studentmanagementapplicationn.models.dto.StudentRegisterModel;
import com.example.studentmanagementapplicationn.models.dto.TeacherRegisterModel;
import com.example.studentmanagementapplicationn.models.dto.UserRegisterModel;
import com.example.studentmanagementapplicationn.entity.university.Student;
import com.example.studentmanagementapplicationn.entity.university.Teacher;
import com.example.studentmanagementapplicationn.entity.users.Role;
import com.example.studentmanagementapplicationn.entity.users.Type;
import com.example.studentmanagementapplicationn.entity.users.User;
import com.example.studentmanagementapplicationn.jwt.JwtUtils;
import com.example.studentmanagementapplicationn.models.LoginUserModel;
import com.example.studentmanagementapplicationn.models.ResponseUserModel;
import com.example.studentmanagementapplicationn.repositories.RoleRepository;
import com.example.studentmanagementapplicationn.repositories.StudentRepository;
import com.example.studentmanagementapplicationn.repositories.TeacherRepository;
import com.example.studentmanagementapplicationn.repositories.UserRepository;
import com.example.studentmanagementapplicationn.auth.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationRESTController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginUserModel loginUserModel) {
        System.out.println(loginUserModel.getPassword());
        System.out.println(loginUserModel.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserModel.getUsername(),
                        loginUserModel.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseUserModel(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup/user")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterModel model) {
        if(userRepository.existsByUsername(model.getUsername())) {
            ResponseEntity.status(409);
        }
        createUser(model);
        return ResponseEntity.ok(new ResponseMessage(Constants.SUCCESSFULLY_REGISTERED_USER));
    }

    private User createUser(UserRegisterModel model) {
        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(encoder.encode(model.getPassword()));
        Role role = roleRepository.findByName(Type.USER.name());
        if (role == null) {
            role = roleRepository.save(new Role(Type.USER));
        }
        user.setRoles(Set.of(role));
        userRepository.save(user);
        return user;
    }

    @PostMapping("/signup/student")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegisterModel model) {
        User user = createUser(model);
        Student student = new Student();
        student.setUser(user);
        student.setName(model.getName());
        student.setAge(model.getAge());
        studentRepository.save(student);
        return ResponseEntity.ok(new ResponseMessage(Constants.SUCCESSFULLY_REGISTERED_STUDENT_MESSAGE));
    }

    @PostMapping("/signup/teacher")
    public ResponseEntity<?> registerTeacher(@RequestBody TeacherRegisterModel model) {
        User user = createUser(model);
        Teacher teacher = new Teacher();
        teacher.setUser(user);
        teacher.setName(model.getName());
        teacher.setDegree(Degree.valueOf(model.getDegree()));
        teacherRepository.save(teacher);
        return ResponseEntity.ok(new ResponseMessage(Constants.SUCCESSFULLY_REGISTERED_TEACHER_MESSAGE));
    }

}
