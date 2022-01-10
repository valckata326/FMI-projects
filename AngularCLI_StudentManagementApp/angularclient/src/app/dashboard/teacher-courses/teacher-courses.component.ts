import { Component, OnInit } from '@angular/core';
import {CoursePlusTeacherModel} from "../../models/CoursePlusTeacherModel";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CourseAverageGrade} from "../../models/CourseAverageGrade";

@Component({
  selector: 'app-teacher-courses',
  templateUrl: './teacher-courses.component.html',
  styleUrls: ['./teacher-courses.component.css']
})
export class TeacherCoursesComponent implements OnInit {
  courses: any = [];
  contentType: any;
  SERVER_URL = 'http://localhost:8080/teacher/getAllCourses/';
  constructor(public httpClient: HttpClient) {
    this.contentType = new HttpHeaders({
    'Content-Type': 'application/json'
    });
    this.takeCoursesWithAverageGrade();
  }

  ngOnInit(): void {
  }

  isTeacher() {
    return localStorage.getItem('roles') === 'TEACHER';
  }

  isStudent() {
    return localStorage.getItem('roles') === 'STUDENT';
  }

  takeCoursesWithAverageGrade() {
    this.SERVER_URL += localStorage.getItem('id');
    this.httpClient.get(this.SERVER_URL, {headers: this.contentType})
      .subscribe(response => {
        const entries = Object.entries(response);
        entries.forEach(entry => {
          let model: CourseAverageGrade;
          model = entry["1"];
          this.courses.push(model);
        })
      });
    //console.log(this.courses);
  }

}
