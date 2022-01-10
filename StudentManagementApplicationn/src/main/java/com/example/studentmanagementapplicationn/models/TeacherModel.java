package com.example.studentmanagementapplicationn.models;

import com.example.studentmanagementapplicationn.entity.base.Degree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeacherModel {
    String name;
    Degree degree;
}
