package com.example.studentmanagementapplicationn.repositories;

import com.example.studentmanagementapplicationn.entity.university.Course;
import com.example.studentmanagementapplicationn.entity.university.Student;
import com.example.studentmanagementapplicationn.entity.university.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    List<StudentCourse> findAllByCourse(Course course);

    List<StudentCourse> findAllByGradeIsNotNull();

    List<StudentCourse> findAllByStudent(Student student);

    boolean existsByCourseAndStudent(Course course, Student student);

    Optional<List<StudentCourse>> findByCourseAndStudent(Course course, Student student);

}
