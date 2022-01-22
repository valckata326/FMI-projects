import {Component, OnInit} from '@angular/core';
import {CourseModel} from "../../models/CourseModel";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {StudentModel} from "../../models/StudentModel";
import {StudentCourseGradeModel} from "../../models/StudentCourseGradeModel";

@Component({
  selector: 'app-add-student-grade-to-course',
  templateUrl: './add-student-grade-to-course.component.html',
  styleUrls: ['./add-student-grade-to-course.component.css']
})
export class AddStudentGradeToCourseComponent implements OnInit {
  courses: Array<CourseModel> = [];
  private GET_COURSES_NAME_AND_IDS = 'http://localhost:8080/course/getCoursesNamesAndIds';
  private GET_STUDENTS_FROM_COURSE = "http://localhost:8080/course/getAllStudentsFromCourse";
  private ADD_STUDENT_GRADE_TO_COURSE = "http://localhost:8080/course/addStudentGrade"
  students: Array<StudentModel> = [];
  private contentType: HttpHeaders;

  constructor(private httpClient: HttpClient) {
    this.contentType = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    this.takeAllCourses();
  }

  ngOnInit(): void {
  }

  takeAllCourses() {
    this.httpClient.get(this.GET_COURSES_NAME_AND_IDS, {headers: this.contentType})
      .subscribe(response => {
        const entries = Object.entries(response);
        entries.forEach(entry => {
          let model: CourseModel;
          model = entry["1"];
          this.courses.push(model);
        })
      });
  }

  takeCourseStudents() {
    // @ts-ignore
    let selectCourse: HTMLSelectElement = document.getElementById("add-grade-course");
    console.log(selectCourse);
    let optionString = selectCourse.options[selectCourse.selectedIndex].value;
    console.log(optionString);
    let courseId: number = Number(optionString.split(" ")[0]);
    console.log(courseId);
    let address = this.GET_STUDENTS_FROM_COURSE + '/' + courseId;
    this.students = [];
    this.httpClient.get(address, {headers: this.contentType})
      .subscribe(response => {
        const entries = Object.entries(response);
        console.log(entries);
        entries.forEach(entry => {
          let model: StudentModel;
          model = entry["1"];
          this.students.push(model);
          //console.log(model);
        })
      });
    if (this.students.length != 0) {
      // @ts-ignore
      document.getElementById('add-grade-students').style.visibility = 'visible';
    } else {
      // @ts-ignore
      document.getElementById(`add-grade-students`).style.visibility = 'visible';
    }
  }

  isTeacher(): boolean {
    return localStorage.getItem("roles") === 'TEACHER';
  }

  isAdmin(): boolean {
    return localStorage.getItem("roles") === 'ADMIN';
  }

  hideDefaultCourse() {
    // @ts-ignore
    document.getElementById('default-course').style.visibility = "hidden";
  }

  makeAddGradeButtonActive() {
    // @ts-ignore
    document.getElementById('add-grade-button').style.visibility = 'visible';
  }

  makeGradeButtonActive() {
    // @ts-ignore
    document.getElementById('add-grade-grade').style.visibility = 'visible';
  }

  addStudentGradeToCourse() {
    // @ts-ignore
    let selectCourse: HTMLSelectElement = document.getElementById("add-grade-course");
    let optionCourse = selectCourse.options[selectCourse.selectedIndex].value;
    let courseID: number = Number(optionCourse.split(" ")[0]);
    
    // @ts-ignore
    let selectStudent: HTMLSelectElement = document.getElementById("add-grade-students");
    let optionStudent = selectStudent.options[selectStudent.selectedIndex].value;
    let studentId: number = Number(optionStudent.split(" ")[0]);
    let address = this.ADD_STUDENT_GRADE_TO_COURSE;

    // @ts-ignore
    let selectGrade: HTMLSelectElement = document.getElementById("add-grade-grade");
    let optionGrade = selectGrade.options[selectGrade.selectedIndex].value;
    let grade: number = Number(optionGrade);

    let studentGradeModel: StudentCourseGradeModel = new StudentCourseGradeModel(studentId, courseID, grade);
    this.httpClient.post(address, JSON.stringify(studentGradeModel), {headers: this.contentType})
      .subscribe(response => {
        console.log(response);
      });
    window.location.reload();
  }
}
