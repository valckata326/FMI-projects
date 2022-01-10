package com.example.studentmanagementapplicationn.services.interfaces;

import com.example.studentmanagementapplicationn.entity.dto.StudentRegisterModel;
import com.example.studentmanagementapplicationn.entity.dto.TeacherRegisterModel;

public interface AuthenticationServiceInterface {
    void registerStudent(StudentRegisterModel studentRegisterModel);

    void registerTeacher(TeacherRegisterModel teacherRegisterModel);
}
