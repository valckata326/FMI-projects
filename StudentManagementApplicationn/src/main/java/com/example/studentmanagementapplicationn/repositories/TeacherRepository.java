package com.example.studentmanagementapplicationn.repositories;

import com.example.studentmanagementapplicationn.entity.university.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
