package com.example.studentmanagementapplicationn.services;

import com.example.studentmanagementapplicationn.constants.Constants;
import com.example.studentmanagementapplicationn.entity.university.Student;
import com.example.studentmanagementapplicationn.exceptions.StudentNotExistsException;
import com.example.studentmanagementapplicationn.models.StudentModel;
import com.example.studentmanagementapplicationn.repositories.StudentRepository;
import com.example.studentmanagementapplicationn.services.interfaces.StudentServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService implements StudentServiceInterface {
    @Autowired
    private final StudentRepository studentRepository;

    @Override
    public void add(Student student) {
        studentRepository.save(student);
    }

    public StudentModel getStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty()) {
            throw new StudentNotExistsException(Constants.STUDENT_NOT_EXISTS_MESSAGE);
        }

        return new StudentModel(student.get());
    }

    public Double getAverageGradeForAStudent(Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if (optionalStudent.isEmpty()) {
            throw new StudentNotExistsException(Constants.STUDENT_NOT_EXISTS_MESSAGE);
        }

        Student student = optionalStudent.get();
        return student.averageGradeForAllCourse();
    }


    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

}
