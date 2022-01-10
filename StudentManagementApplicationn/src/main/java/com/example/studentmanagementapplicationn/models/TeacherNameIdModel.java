package com.example.studentmanagementapplicationn.models;

import com.example.studentmanagementapplicationn.entity.university.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherNameIdModel {
    String teacherName;
    Long teacherId;

    public TeacherNameIdModel(Teacher teacher) {
        this.teacherId = teacher.getId();
        this.teacherName = teacher.getName();
    }
}
