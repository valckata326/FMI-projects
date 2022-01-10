import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CoursePlusTeacherModel} from "../../models/CoursePlusTeacherModel";

@Component({
  selector: 'app-courses-teacher-table',
  templateUrl: './courses-teacher-table.component.html',
  styleUrls: ['./courses-teacher-table.component.css']
})
export class CoursesTeacherTableComponent implements OnInit {
  contentType: any;
  courses: Array<any> = [];
  SERVER_URL = 'http://localhost:8080/teacher/getAllCoursesWithTheirTeacher'

  constructor(public httpClient: HttpClient) {
    this.contentType = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    this.takeCoursesWithTeacherName();
  }

  ngOnInit(): void {
  }

  takeCoursesWithTeacherName() {
    this.httpClient.get(this.SERVER_URL, {headers: this.contentType})
      .subscribe(response => {
        const entries = Object.entries(response);
        entries.forEach(entry => {
          let model: CoursePlusTeacherModel;
          model = entry["1"];
          this.courses.push(model);
          //console.log(model);
        })
      });
    //console.log(this.courses);
  }

  isStudent() {
    return localStorage.getItem('roles') === 'STUDENT';
  }

  isTeacher() {
    return localStorage.getItem('roles') === 'TEACHER';
  }

}
