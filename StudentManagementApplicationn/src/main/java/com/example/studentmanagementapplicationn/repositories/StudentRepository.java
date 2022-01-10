package com.example.studentmanagementapplicationn.repositories;

import com.example.studentmanagementapplicationn.entity.university.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
