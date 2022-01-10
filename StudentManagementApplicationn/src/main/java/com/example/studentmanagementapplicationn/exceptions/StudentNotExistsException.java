package com.example.studentmanagementapplicationn.exceptions;

import com.example.studentmanagementapplicationn.exceptions.baseexception.BaseException;

public class StudentNotExistsException extends BaseException {
    public StudentNotExistsException(String message) {
        super(message);
    }
}
