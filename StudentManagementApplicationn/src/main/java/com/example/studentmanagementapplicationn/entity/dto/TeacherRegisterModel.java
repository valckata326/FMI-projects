package com.example.studentmanagementapplicationn.entity.dto;

import com.example.studentmanagementapplicationn.entity.base.Degree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRegisterModel extends UserRegisterModel {
    private String degree;
}
