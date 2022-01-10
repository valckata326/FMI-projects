package com.example.studentmanagementapplicationn.entity.university;

import com.example.studentmanagementapplicationn.entity.base.NamedEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course extends NamedEntity implements Comparable<Course> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )

    private Teacher teacher;

    @Column
    private int totalHours;
    @OneToMany (
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonIgnore
    private Set<StudentCourse> studentGrades;

    public Course(String name, int totalHours) {
        super(name);
        this.totalHours = totalHours;
        studentGrades = new HashSet<>();
    }

    public boolean addStudent(StudentCourse studentCourse) {
        if (studentGrades.contains(studentCourse)) {
            return false;
        }

        studentGrades.add(studentCourse);
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return this.getId().equals(course.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public int compareTo(Course o) {
        return this.getName().compareTo(o.getName());
    }
    @JsonIgnore
    public double getAverageGradeForAllStudents() {

        if (studentGrades.isEmpty()) {
            return 2.0;
        }

        Double sumGrades = studentGrades
                .stream()
                .map(StudentCourse::getGrade)
                .reduce(Double::sum)
                .orElse(2.0);

        return sumGrades / studentGrades.size();
    }

    public void sortGrades() {
        studentGrades = studentGrades.stream()
                .sorted(Comparator.comparingDouble(StudentCourse::getGrade))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

}
