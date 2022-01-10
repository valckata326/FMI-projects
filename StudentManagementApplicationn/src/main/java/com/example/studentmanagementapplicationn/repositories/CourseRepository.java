package com.example.studentmanagementapplicationn.repositories;

import com.example.studentmanagementapplicationn.entity.university.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
