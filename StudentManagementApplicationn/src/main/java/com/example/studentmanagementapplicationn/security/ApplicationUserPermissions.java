package com.example.studentmanagementapplicationn.security;

public enum ApplicationUserPermissions {
    /**
     * Student permissions
     */
    SEE_AVERAGE_GRADE("averageGrade"),
    SEE_AVERAGE_GRADE_IN_COURSE("averageGradeInCourse"),
    SEE_COURSE_INFORMATION("courseInformation"),

    /**
     * Teacher permissions (+Student permissions)
     */
    ADD_GRADE_TO_STUDENT("addGradeToStudent"),
    ADD_STUDENT_TO_COURSE("addStudentToCourse"),

    /**
     * Admin permissions (+ Teacher and Student permissions)
     */
    ADD_TEACHER_TO_COURSE("addTeacherToCourse"),
    CHANGE_ROLE("changeRole");

    private final String permission;

    ApplicationUserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
