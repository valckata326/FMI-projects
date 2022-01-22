package com.example.studentmanagementapplicationn.services.interfaces;

import com.example.studentmanagementapplicationn.models.dto.StudentRegisterModel;
import com.example.studentmanagementapplicationn.models.dto.TeacherRegisterModel;

public interface AuthenticationServiceInterface {
    void registerStudent(StudentRegisterModel studentRegisterModel);

    void registerTeacher(TeacherRegisterModel teacherRegisterModel);
}
