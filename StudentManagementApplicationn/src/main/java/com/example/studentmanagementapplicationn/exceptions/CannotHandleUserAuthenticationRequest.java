package com.example.studentmanagementapplicationn.exceptions;

import com.example.studentmanagementapplicationn.exceptions.baseexception.BaseException;

public class CannotHandleUserAuthenticationRequest extends BaseException {
    public CannotHandleUserAuthenticationRequest(String message) {
        super(message);
    }
}
