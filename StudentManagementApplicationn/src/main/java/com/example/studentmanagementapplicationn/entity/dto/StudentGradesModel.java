package com.example.studentmanagementapplicationn.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentGradesModel implements Comparable<StudentGradesModel>{
    private String courseName;
    private String studentName;
    private Double studentGrade;

    @Override
    public int compareTo(@NotNull StudentGradesModel o) {
        return Comparator.comparing(StudentGradesModel::getCourseName)
                .thenComparing(StudentGradesModel::getStudentGrade)
                .compare(this, o);
    }
}
