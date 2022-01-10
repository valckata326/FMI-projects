import { Component, OnInit } from '@angular/core';
import {CourseModel} from "../../models/CourseModel";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TeacherNameIdModel} from "../../models/TeacherNameIdModel";

@Component({
  selector: 'app-add-teacher-to-course',
  templateUrl: './add-teacher-to-course.component.html',
  styleUrls: ['./add-teacher-to-course.component.css']
})
export class AddTeacherToCourseComponent implements OnInit {
  courses: Array<CourseModel> = [];
  private GET_COURSES_NAME_AND_IDS = 'http://localhost:8080/course/getCoursesWithoutTeachers';
  private GET_ALL_TEACHERS = "http://localhost:8080/teacher/getAllTeachers";
  private ADD_TEACHER_TO_COURSE = "http://localhost:8080/admin/setTeacher/";
  private contentType: HttpHeaders;
  teachers: Array<TeacherNameIdModel> = [];


  constructor(private httpClient: HttpClient) {
    this.contentType = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    this.takeAllCoursesWithNoTeacher();
    this.takeAllTeachers();
  }

  ngOnInit(): void {
  }

  isAdmin(): boolean {
    return localStorage.getItem('roles') === 'ADMIN';
  }

  isTeacher(): boolean {
    return localStorage.getItem('roles') === 'TEACHER';
  }

  takeAllCoursesWithNoTeacher() {
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

  takeAllTeachers() {
    this.httpClient.get(this.GET_ALL_TEACHERS, {headers: this.contentType})
      .subscribe(response => {
        const entries = Object.entries(response);
        entries.forEach(entry => {
          let model: TeacherNameIdModel;
          model = entry["1"];
          this.teachers.push(model);
        })
      });
  }

  makeTeachersVisible() {
    // @ts-ignore
    document.getElementById('add-teacher-teacher').style.visibility = "visible";
  }

  makeAddTeacherForCourseButtonVisible() {
    // @ts-ignore
    document.getElementById('add-teacher-to-course-button').style.visibility = "visible";
  }

  addTeacherToCourse() {
    // @ts-ignore
    let selectCourse: HTMLSelectElement = document.getElementById("add-teacher-course");
    let optionCourse = selectCourse.options[selectCourse.selectedIndex].value;
    let courseID: number = Number(optionCourse.split(" ")[0]);
    // @ts-ignore
    let selectTeacher: HTMLSelectElement = document.getElementById("add-teacher-teacher");
    let optionString = selectTeacher.options[selectTeacher.selectedIndex].value;
    let teacherId: number = Number(optionString.split(" ")[0]);
    let address = this.ADD_TEACHER_TO_COURSE + teacherId + '/' + courseID;
    this.httpClient.get(address, {headers: this.contentType})
      .subscribe(response => {
        console.log(response);
      });
  }
}
