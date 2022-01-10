package com.example.studentmanagementapplicationn.exceptions;

import com.example.studentmanagementapplicationn.exceptions.baseexception.BaseException;

public class StudentNotAttendingThisCourse extends BaseException {
    public StudentNotAttendingThisCourse(String message) {
        super(message);
    }
}
