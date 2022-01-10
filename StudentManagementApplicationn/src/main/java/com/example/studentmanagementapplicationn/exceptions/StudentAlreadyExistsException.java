package com.example.studentmanagementapplicationn.exceptions;

import com.example.studentmanagementapplicationn.exceptions.baseexception.BaseException;

public class StudentAlreadyExistsException extends BaseException {
    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}
