package com.example.studentmanagementapplicationn.services;

import com.example.studentmanagementapplicationn.entity.base.Degree;
import com.example.studentmanagementapplicationn.models.dto.StudentRegisterModel;
import com.example.studentmanagementapplicationn.models.dto.TeacherRegisterModel;
import com.example.studentmanagementapplicationn.models.dto.UserRegisterModel;
import com.example.studentmanagementapplicationn.entity.university.Student;
import com.example.studentmanagementapplicationn.entity.university.Teacher;
import com.example.studentmanagementapplicationn.entity.users.Role;
import com.example.studentmanagementapplicationn.entity.users.Type;
import com.example.studentmanagementapplicationn.entity.users.User;
import com.example.studentmanagementapplicationn.repositories.RoleRepository;
import com.example.studentmanagementapplicationn.repositories.StudentRepository;
import com.example.studentmanagementapplicationn.repositories.TeacherRepository;
import com.example.studentmanagementapplicationn.repositories.UserRepository;
import com.example.studentmanagementapplicationn.services.interfaces.AuthenticationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService implements AuthenticationServiceInterface {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 StudentRepository studentRepository,
                                 TeacherRepository teacherRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }


    @Override
    public void registerTeacher(TeacherRegisterModel model) {
        User user = createUser(model);
        //TODO: add user details
        Teacher teacher = new Teacher();
        teacher.setName(model.getName());
        teacher.setDegree(Degree.valueOf(model.getDegree()));
        teacher.setUser(user);
        teacherRepository.save(teacher);
    }

    @Override
    public void registerStudent(StudentRegisterModel model) {
        User user = createUser(model);
        Student student = new Student();
        student.setName(model.getName());
        student.setAge(model.getAge());
        student.setUser(user);
        studentRepository.save(student);
    }

    private User createUser(UserRegisterModel model) {
        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(model.getPassword());
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(createDefaultRole());
        user.setRoles(userRoles);
        userRepository.save(user);
        return user;
    }

    private Role createDefaultRole() {
        Role role = roleRepository.findByName(Type.USER.name());
        if (role == null) {
            role = roleRepository.save(new Role(Type.USER));
        }
        return role;
    }
}
