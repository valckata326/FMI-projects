import { Component, OnInit } from '@angular/core';
import {CourseStudentsModel} from "../../models/CourseStudentsModel";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-course-students',
  templateUrl: './course-students.component.html',
  styleUrls: ['./course-students.component.css']
})
export class CourseStudentsComponent implements OnInit {
  coursesWithStudents: Array<CourseStudentsModel> = [];
  SERVER_URL = 'http://localhost:8080/course/getCourseWithStudents';
  contentType: HttpHeaders;
  constructor(public httpClient: HttpClient) {
    this.contentType = new HttpHeaders({
    'Content-Type': 'application/json'
    });
    this.takeAllCoursesWithTheirStudents();
  }

  ngOnInit(): void {
  }

  takeAllCoursesWithTheirStudents() {
    this.httpClient.get(this.SERVER_URL, {headers: this.contentType})
      .subscribe(response => {
        const entries = Object.entries(response);
        entries.forEach(entry => {
          let model: CourseStudentsModel;
          model = entry["1"];
          this.coursesWithStudents.push(model);
          console.log(model);
        })
      });
  }
  isTeacher() {
     return localStorage.getItem('roles') === 'TEACHER';
  }

  isAdmin() {
    return localStorage.getItem('roles') === 'ADMIN';
  }
}
