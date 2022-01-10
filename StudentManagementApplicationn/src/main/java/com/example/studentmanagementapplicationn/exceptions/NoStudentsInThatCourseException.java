package com.example.studentmanagementapplicationn.exceptions;

import com.example.studentmanagementapplicationn.exceptions.baseexception.BaseException;

public class NoStudentsInThatCourseException extends BaseException {
    public NoStudentsInThatCourseException(String message) {
        super(message);
    }
}
