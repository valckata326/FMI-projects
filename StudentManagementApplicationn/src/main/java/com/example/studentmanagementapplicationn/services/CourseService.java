package com.example.studentmanagementapplicationn.services;

import com.example.studentmanagementapplicationn.constants.Constants;
import com.example.studentmanagementapplicationn.entity.ResponseMessage;
import com.example.studentmanagementapplicationn.entity.university.Course;
import com.example.studentmanagementapplicationn.entity.university.Student;
import com.example.studentmanagementapplicationn.entity.university.StudentCourse;
import com.example.studentmanagementapplicationn.entity.university.Teacher;
import com.example.studentmanagementapplicationn.exceptions.CourseNotExistException;
import com.example.studentmanagementapplicationn.exceptions.StudentAlreadyExistsException;
import com.example.studentmanagementapplicationn.exceptions.StudentNotExistsException;
import com.example.studentmanagementapplicationn.exceptions.TeacherNotExistsException;
import com.example.studentmanagementapplicationn.models.*;
import com.example.studentmanagementapplicationn.repositories.CourseRepository;
import com.example.studentmanagementapplicationn.repositories.StudentCourseRepository;
import com.example.studentmanagementapplicationn.repositories.StudentRepository;
import com.example.studentmanagementapplicationn.repositories.TeacherRepository;
import com.example.studentmanagementapplicationn.services.interfaces.CourseServiceInterface;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService implements CourseServiceInterface {
    private CourseRepository courseRepository;

    private TeacherRepository teacherRepository;

    private StudentRepository studentRepository;

    private StudentCourseRepository studentCourseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository,
                         TeacherRepository teacherRepository,
                         StudentRepository studentRepository,
                         StudentCourseRepository studentCourseRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.studentCourseRepository = studentCourseRepository;

    }

    @Override
    public void addStudentToCourse(Long studentId, Long courseId) {
        Course foundCourse = this.courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new CourseNotExistException(Constants.COURSE_NOT_EXISTS_MESSAGE));
        Student foundStudent = this.studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotExistsException(Constants.STUDENT_NOT_EXISTS_MESSAGE));
        if (studentCourseRepository.existsByCourseAndStudent(foundCourse, foundStudent)) {
            throw new StudentAlreadyExistsException(Constants.Student_ALREADY_EXISTS_MESSAGE);
        }
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourse(foundCourse);
        studentCourse.setStudent(foundStudent);
        studentCourse.setGrade(Constants.INVALID_GRADE);
        studentCourseRepository.save(studentCourse);
        /*foundStudent.addCourse(studentCourse);
        foundCourse.addStudent(studentCourse);
        this.studentRepository.save(foundStudent);
        this.courseRepository.save(foundCourse);
         */
    }

    @Override
    public List<Course> getAllCourses() {
        return this.courseRepository.findAll();
    }

    @Override
    public List<CourseTeacherModel> getCoursesNamesWithTheirTeacher() {
        List<CourseTeacherModel> courseTeacherModels = new ArrayList<>();
        getAllCourses().forEach(course -> {
            courseTeacherModels.add(new CourseTeacherModel(course.getName(),
                    course.getTeacher()));
        });
        return courseTeacherModels;
    }

    @Override
    public List<CourseStudentModel> getCoursesWithStudents() {
        final List<StudentCourse> allStudentCourse = studentCourseRepository.findAll();
        List<CourseStudentModel> courseStudentModels = new ArrayList<>();
        List<Course> courses = allStudentCourse.stream()
                .map(StudentCourse::getCourse)
                .distinct()
                .collect(Collectors.toList());
        courses.forEach(course -> {
            List<StudentCourse> courseRecord = studentCourseRepository.findAllByCourse(course);
            Set<Student> students = getStudents(courseRecord);
            List<StudentGradeModel> studentGradeModels = new ArrayList<>();
            students.forEach(student -> {
                final Optional<List<StudentCourse>> byCourseAndStudent
                        = studentCourseRepository.findByCourseAndStudent(course, student);
                final double averageGrade = getAverageGrade(byCourseAndStudent);
                StudentGradeModel currRecord = new StudentGradeModel(student.getName(), averageGrade);
                studentGradeModels.add(currRecord);
            });
            Double totalAverage = getCourseAverageGrade(studentGradeModels);
            CourseStudentModel current = new CourseStudentModel(course.getName(), studentGradeModels, totalAverage);
            courseStudentModels.add(current);
        });
        return courseStudentModels;
    }

    @Override
    public List<CourseNameIDModel> getAllCoursesNameAndId() {
        final List<Course> allCourses = this.courseRepository.findAll();
        List<CourseNameIDModel> courseModels = new ArrayList<>();
        allCourses.forEach(course -> {
            CourseNameIDModel courseNameIDModel = new CourseNameIDModel(course);
            courseModels.add(courseNameIDModel);
        });
        return courseModels;
    }

    @Override
    public List<StudentNameIdModel> getAllStudentsAvailableForCourse(Long id) {
        final Optional<Course> courseById = this.courseRepository.findById(id);
        if(courseById.isPresent()) {
            final List<Student> courseStudents = courseById.get().getStudentGrades().stream()
                    .map(StudentCourse::getStudent)
                    .collect(Collectors.toList());
            final List<Student> allStudents = studentRepository.findAll();
            allStudents.removeAll(courseStudents);
            List<StudentNameIdModel> returnCollection = new ArrayList<>();
            allStudents.forEach(student -> {
                StudentNameIdModel model = new StudentNameIdModel(student);
                returnCollection.add(model);
            });
            return returnCollection;
        }
        return new ArrayList<>();
    }

    @Override
    public Set<StudentNameIdModel> getAllStudentsFromCourse(Long courseId) {
        final Optional<Course> definiteCouse = this.courseRepository.findById(courseId);
        if(definiteCouse.isPresent()) {
            final Set<Student> studentsFromCourse = definiteCouse.get().getStudentGrades().stream()
                    .map(StudentCourse::getStudent)
                    .collect(Collectors.toSet());
            Set<StudentNameIdModel> returnCollection = new HashSet<>();
            studentsFromCourse.forEach(student -> {
                StudentNameIdModel model = new StudentNameIdModel(student);
                returnCollection.add(model);
            });
            return returnCollection;
        }
        return new HashSet<>();
    }

    @Override
    public Set<CourseNameIDModel> getCoursesWithoutTeacher() {
        final List<Course> allCourses = this.courseRepository.findAll();
        final List<Course> coursesWithoutTeacher = allCourses.stream()
                .filter(course -> course.getTeacher() == null)
                .collect(Collectors.toList());
        Set<CourseNameIDModel> courseNameIDModels = new HashSet<>();
        coursesWithoutTeacher.forEach(course -> {
            CourseNameIDModel current = new CourseNameIDModel(course);
            courseNameIDModels.add(current);
        });
        return courseNameIDModels;
    }

    @NotNull
    private Double getCourseAverageGrade(List<StudentGradeModel> studentGradeModels) {
        return studentGradeModels.stream()
                .mapToDouble(StudentGradeModel::getGrade)
                .filter(grade -> grade != 0.0)
                .average()
                .orElse(2.0);
    }

    private double getAverageGrade(Optional<List<StudentCourse>> byCourseAndStudent) {
        return byCourseAndStudent.get().stream()
                .mapToDouble(StudentCourse::getGrade)
                .filter(grade -> grade != 0.0)
                .average()
                .orElse(0.0);
    }

    @NotNull
    private Set<Student> getStudents(List<StudentCourse> courseRecord) {
        return courseRecord.stream()
                .map(StudentCourse::getStudent)
                .collect(Collectors.toSet());
    }

    @Override
    public void add(Course course) {
        this.courseRepository.save(course);
    }

    @Override
    public void setTeacher(Long teacherId, Long courseId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotExistsException(Constants.TEACHER_NOT_EXISTS_MESSAGE));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotExistException(Constants.COURSE_NOT_EXISTS_MESSAGE));
        course.setTeacher(teacher);
        teacher.getCourses().add(course);
        courseRepository.save(course);
        teacherRepository.save(teacher);
    }

}
