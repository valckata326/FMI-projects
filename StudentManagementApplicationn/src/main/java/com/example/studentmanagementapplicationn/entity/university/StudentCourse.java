package com.example.studentmanagementapplicationn.entity.university;

import com.example.studentmanagementapplicationn.entity.base.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse extends BasicEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Student student;

    @Column
    private Double grade;

    public StudentCourse(Student student, Course course, Double grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentCourse)) return false;
        StudentCourse that = (StudentCourse) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
