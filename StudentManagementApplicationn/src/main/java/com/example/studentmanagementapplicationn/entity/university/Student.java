package com.example.studentmanagementapplicationn.entity.university;

import com.example.studentmanagementapplicationn.constants.Constants;
import com.example.studentmanagementapplicationn.entity.users.User;
import com.example.studentmanagementapplicationn.entity.base.NamedEntity;
import com.example.studentmanagementapplicationn.models.CourseNameAverageGradeModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;


@Entity
@Setter
@Getter
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student extends NamedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private int age;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @Column
    @JsonIgnore
    private Set<StudentCourse> courses;

    @OneToOne
    @MapsId
    @JoinColumn
    public User user;

    public void addCourse(StudentCourse course) {
        this.courses.add(course);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return this.getId().equals(student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Transient
    public Double averageGradeForAllCourse() {
        if (courses.isEmpty()) {
            return 2D;
        }

        Map<String, List<Double>> studentCoursesGrades = new HashMap<>();
        List<CourseNameAverageGradeModel> studentCourseNameList = new ArrayList<>();
        for (StudentCourse current : courses) {
            studentCoursesGrades.putIfAbsent(current.getCourse().getName(), new ArrayList<>());
            if (current.getGrade() != Constants.INVALID_GRADE) {
                studentCoursesGrades.get(current.getCourse().getName()).add(current.getGrade());
            }
        }
        for (Map.Entry<String, List<Double>> current : studentCoursesGrades.entrySet()) {
            OptionalDouble gradeForCourse = current.getValue().stream().mapToDouble(a -> a).average();
            CourseNameAverageGradeModel currentModel
                    = new CourseNameAverageGradeModel(current.getKey(), !gradeForCourse.isEmpty()
                    ? gradeForCourse.getAsDouble() : 0);
            studentCourseNameList.add(currentModel);
        }
        return studentCourseNameList.stream()
                .map(CourseNameAverageGradeModel::getAverageGrade)
                .mapToDouble(a -> a).average().getAsDouble();
    }
}
