import {Component, OnInit} from '@angular/core';
import {CourseModel} from "../../models/CourseModel";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {StudentModel} from "../../models/StudentModel";
import {CourseService} from "../../_services/management/CourseService";

@Component({
  selector: 'app-add-student-to-course',
  templateUrl: './add-student-to-course.component.html',
  styleUrls: ['./add-student-to-course.component.css']
})
export class AddStudentToCourseComponent implements OnInit {
  get courses(): Array<CourseModel> {
    return this._courses;
  }

  get students(): Array<StudentModel> {
    return this._students;
  }

  private _courses: Array<CourseModel> = [];
  public GET_COURSES_NAME_AND_IDS = 'http://localhost:8080/course/getCoursesNamesAndIds';
  public GET_AVAILABLE_STUDENTS_FOR_COURSE = 'http://localhost:8080/course/getStudentsAvailableForCourse/';
  public ADD_STUDENT_TO_COURSE_URL = 'http://localhost:8080/course/addStudentToCourse/';
  private contentType: HttpHeaders;
  private _students: Array<StudentModel> = [];
  private courseService: CourseService = new CourseService(this.httpClient);

  constructor(private httpClient: HttpClient) {
    this.contentType = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    this.takeAllCourses();
  }

  ngOnInit(): void {

  }

  takeAllCourses() {
    this.courseService.takeAllCourses(this._courses, this.GET_COURSES_NAME_AND_IDS);
  }

  filterStudents(): void {
    // @ts-ignore
    let selectCourse: HTMLSelectElement = document.getElementById("course");
    let optionString = selectCourse.options[selectCourse.selectedIndex].value;
    let courseId: number = Number(optionString.split(" ")[0]);
    let address = this.GET_AVAILABLE_STUDENTS_FOR_COURSE + courseId;
    this._students = [];
    this.httpClient.get(address, {headers: this.contentType})
      .subscribe(response => {
        const entries = Object.entries(response);
        entries.forEach(entry => {
          let model: StudentModel;
          model = entry["1"];
          this._students.push(model);
        })
      });
    if (this._students.length != 0) {
      // @ts-ignore
      document.getElementById('students').style.visibility = 'visible';
    } else {
      // @ts-ignore
      document.getElementById(`students`).style.visibility = 'visible';
    }
  }

  isTeacher(): boolean {
    return localStorage.getItem("roles") === 'TEACHER';
  }

  isAdmin(): boolean {
    return localStorage.getItem("roles") === 'ADMIN';
  }

  addStudentToCourse() {
    // @ts-ignore
    let selectCourse: HTMLSelectElement = document.getElementById("course");
    let optionCourse = selectCourse.options[selectCourse.selectedIndex].value;
    let courseID: number = Number(optionCourse.split(" ")[0]);
    // @ts-ignore
    let selectStudent: HTMLSelectElement = document.getElementById("students");
    let optionString = selectStudent.options[selectStudent.selectedIndex].value;
    let studentId: number = Number(optionString.split(" ")[0]);
    let address = this.ADD_STUDENT_TO_COURSE_URL + courseID + '/' + studentId;
    this.httpClient.get(address, {headers: this.contentType})
      .subscribe(response => {
        console.log(response);
        window.location.reload();
      });
  }

  makeAddButtonVisible() {
    // @ts-ignore
    document.getElementById('add-student-button').style.visibility = 'visible';
  }
}
