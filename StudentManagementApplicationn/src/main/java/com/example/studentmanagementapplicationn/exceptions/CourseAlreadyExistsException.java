package com.example.studentmanagementapplicationn.exceptions;

import com.example.studentmanagementapplicationn.exceptions.baseexception.BaseException;

public class CourseAlreadyExistsException extends BaseException {
    public CourseAlreadyExistsException(String message) {
        super(message);
    }
}
