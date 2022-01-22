package com.example.studentmanagementapplicationn.services;

import com.example.studentmanagementapplicationn.constants.Constants;
import com.example.studentmanagementapplicationn.models.dto.CoursePlusTeacherModel;
import com.example.studentmanagementapplicationn.entity.university.Course;
import com.example.studentmanagementapplicationn.entity.university.Teacher;
import com.example.studentmanagementapplicationn.exceptions.TeacherNotExistsException;
import com.example.studentmanagementapplicationn.models.CourseNameAverageGradeModel;
import com.example.studentmanagementapplicationn.models.TeacherNameIdModel;
import com.example.studentmanagementapplicationn.repositories.CourseRepository;
import com.example.studentmanagementapplicationn.repositories.TeacherRepository;
import com.example.studentmanagementapplicationn.services.interfaces.TeacherServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TeacherService implements TeacherServiceInterface {
    @Autowired
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;

    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public List<CourseNameAverageGradeModel> getAllCoursesByTeacher(Long teacherId) {
        Teacher teacher = getTeacherById(teacherId);
        List<Course> allCoursesToTeacher = teacher.getCourses();
        List<CourseNameAverageGradeModel> courseModels = new ArrayList<>();
        allCoursesToTeacher.forEach((course) -> {
            CourseNameAverageGradeModel current = new CourseNameAverageGradeModel(course);
            courseModels.add(current);
        });
        return courseModels;

    }

    @Override
    public List<CoursePlusTeacherModel> getAllCoursesWithTheirTeachers() {
        List<Course> allCourses = courseRepository.findAll();
        List<CoursePlusTeacherModel> allCoursesWithTheirTeachers = new ArrayList<>();
        allCourses.forEach((course -> {
            CoursePlusTeacherModel coursePlusTeacherModel = new CoursePlusTeacherModel(course);
            allCoursesWithTheirTeachers.add(coursePlusTeacherModel);
        }));
        return allCoursesWithTheirTeachers;
    }

    public Teacher getTeacherById(Long id) {
        return this.teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotExistsException(Constants.TEACHER_NOT_EXISTS_MESSAGE));
    }


    @Override
    public Set<TeacherNameIdModel> getAllTeachers() {
        final List<Teacher> allTeachers = this.teacherRepository.findAll();
        Set<TeacherNameIdModel> teacherModels = new HashSet<>();
        allTeachers.forEach(teacher -> {
            TeacherNameIdModel current = new TeacherNameIdModel(teacher);
            teacherModels.add(current);
        });
        return teacherModels;
    }

}
