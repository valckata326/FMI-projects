package com.example.studentmanagementapplicationn.services;

import com.example.studentmanagementapplicationn.comparators.CustomComparator;
import com.example.studentmanagementapplicationn.constants.Constants;
import com.example.studentmanagementapplicationn.entity.university.Course;
import com.example.studentmanagementapplicationn.entity.university.Student;
import com.example.studentmanagementapplicationn.entity.university.StudentCourse;
import com.example.studentmanagementapplicationn.exceptions.*;
import com.example.studentmanagementapplicationn.models.CourseNameAverageGradeModel;
import com.example.studentmanagementapplicationn.repositories.CourseRepository;
import com.example.studentmanagementapplicationn.repositories.StudentCourseRepository;
import com.example.studentmanagementapplicationn.repositories.StudentRepository;
import com.example.studentmanagementapplicationn.services.interfaces.StudentGradesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentGradesService implements StudentGradesServiceInterface {

    private final StudentCourseRepository studentCourseRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentGradesService(StudentCourseRepository academicRecordRepository,
                                CourseRepository courseRepository,
                                StudentRepository studentRepository) {
        this.studentCourseRepository = academicRecordRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public double averageGradeInCourse(Long courseId) {
        Course foundCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotExistException(Constants.COURSE_NOT_EXISTS_MESSAGE));

        return studentCourseRepository.findAllByCourse(foundCourse)
                .stream()
                .mapToDouble(StudentCourse::getGrade)
                .average()
                .orElseThrow(() -> new NoStudentsInThatCourseException(
                        Constants.NO_STUDENTS_IN_THAT_COURSE_MESSAGE));
    }

    @Override
    public double averageStudentGradeForAllCourses(Long studentId) {
        Student foundStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotExistsException(Constants.STUDENT_NOT_EXISTS_MESSAGE));
        return studentCourseRepository.findAllByStudent(foundStudent)
                .stream()
                .mapToDouble(StudentCourse::getGrade)
                .average()
                .orElseThrow(() -> new NoStudentGradesException(Constants.NO_STUDENT_GRADES_MESSAGE));
    }

    @Override
    public void addStudentGradeToCourse(Long studentId, Long courseId, double grade) {
        if (grade < Constants.DOWN_GRADE || grade > Constants.UP_GRADE) {
            throw new InvalidGradeException(Constants.INVALID_GRADE_MESSAGE);
        }
        Student foundStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotExistsException(Constants.STUDENT_NOT_EXISTS_MESSAGE));
        Course foundCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotExistException(Constants.COURSE_NOT_EXISTS_MESSAGE));
        StudentCourse addGrade = new StudentCourse(foundStudent, foundCourse, grade);
        studentCourseRepository.save(addGrade);
    }

    @Override
    public List<CourseNameAverageGradeModel> getAllCoursesWithAverageGradeForStudent(Long studentId) {
        Student definiteStudent = this.studentRepository.getById(studentId);
        List<StudentCourse> studentCourses = new ArrayList<>(this.studentCourseRepository
                .findAllByStudent(definiteStudent));
        Map<String, List<Double>> studentCoursesGrades = new HashMap<>();
        List<CourseNameAverageGradeModel> studentCourseNameList = new ArrayList<>();
        if (!studentCourses.isEmpty()) {
            for (StudentCourse current : studentCourses) {
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
        }
        return studentCourseNameList;

    }

    @Override
    public List<Course> getStudentsSorted() {
        List<Course> courses = this.courseRepository
                .findAll()
                .stream()
                .sorted(CustomComparator::compareCourses)
                .collect(Collectors.toList());
        courses.forEach(Course::sortGrades);
        return courses;

    }
}
