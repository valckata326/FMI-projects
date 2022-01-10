package com.example.studentmanagementapplicationn.models;

import com.example.studentmanagementapplicationn.entity.university.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseTeacherModel {
    String courseName;
    String teacherName;

    public CourseTeacherModel(String courseName, Teacher teacher) {
        this.courseName = courseName;
        handleTeacher(teacher);
    }

    private void handleTeacher(Teacher teacher) {
        if(teacher == null) {
            this.teacherName = "Not assigned";
        }
        else {
            this.teacherName = teacher.getName();
        }
    }
}
