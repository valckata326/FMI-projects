package com.example.studentmanagementapplicationn.exceptions;

import com.example.studentmanagementapplicationn.exceptions.baseexception.BaseException;

public class CourseNotExistException extends BaseException {
    public CourseNotExistException(String message) {
        super(message);
    }
}
