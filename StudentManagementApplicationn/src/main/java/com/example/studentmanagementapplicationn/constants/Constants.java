package com.example.studentmanagementapplicationn.constants;

public interface Constants {
    double INVALID_GRADE = 0.0;
    String COURSES_STRING = "-----Courses-----";
    String NAME_QUOTES = "Name: ";
    String DEGREE_QUOTE = "Degree: ";

    int EMPTY_COLLECTION = 0;
    double DOWN_GRADE = 2.0;
    double UP_GRADE = 6.0;
    String ALREADY_EXISTS = " already exists!";
    String DOES_NOT_EXIST = "does not exist!";
    String NOT_VALID_GRADE = "is not a valid grade";

    String COURSE_NOT_EXISTS_MESSAGE = "Course with this id is not found";
    String TEACHER_NOT_EXISTS_MESSAGE = "Teacher with this id is not found";
    String STUDENT_NOT_EXISTS_MESSAGE = "Student with that id is not found";
    String Student_ALREADY_EXISTS_MESSAGE = "Student already exists in this course";
    String USERNAME_NOT_FOUND_TEMPLATE = "Username %s was not found";
    String CANNOT_HANDLE_USER_AUTHENTICATION_REQUEST = "There is a problem with user authentication";
    String NO_STUDENTS_IN_THAT_COURSE_MESSAGE = "There are no students in that course";
    String NO_STUDENT_GRADES_MESSAGE = "This student has no grades";
    String INVALID_GRADE_MESSAGE = "Invalid grade!. It should be in [2.0, 6.0]";
    String STUDENT_NOT_ATTENDING_COURSE_MESSAGE = "This student is not attending that course";
    String USER_NOT_FOUND_MESSAGE = "User with this username is not found";
    String SUCCESSFULLY_ADDED_STUDENT_TO_COURSE = "Successfully added student to course";
    String SUCCESSFULLY_ADDED_STUDENT_GRADE_TO_COURSE = "Successfully added student grade to course!";
    String SUCCESSFULLY_REGISTERED_TEACHER_MESSAGE = "Successfully added teacher to the system!";
    String SUCCESSFULLY_SET_TEACHER_TO_COURSE = "Successfully appointed teacher to course!";
    String SUCCESSFULLY_CHANGED_ROLES_TO_THAT_USER = "Successfully changed roles to that user";
    String SUCCESSFULLY_ADDED_COURSE = "Successfully added course";
    String SUCCESSFULLY_ADDED_STUDENT = "Successfully added student";
    String SUCCESSFULLY_ADDED_TEACHER = "Successfully added teacher";
    String SUCCESSFULLY_REGISTERED_USER = "User registered successfully!";
    String SUCCESSFULLY_REGISTERED_STUDENT_MESSAGE = "Successfully added student!";
}
