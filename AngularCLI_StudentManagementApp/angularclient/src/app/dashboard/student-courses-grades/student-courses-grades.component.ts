import { Component, OnInit } from '@angular/core';
import { CourseAverageGrade} from '../../models/CourseAverageGrade'
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-student-courses-grades',
  templateUrl: './student-courses-grades.component.html',
  styleUrls: ['./student-courses-grades.component.css']
})
export class StudentCoursesGradesComponent implements OnInit {
  contentType: any;
  courses: Array<any> = [];
  SERVER_URL = 'http://localhost:8080/student/getStudentCoursesWithGrade/'
  constructor(public httpClient: HttpClient) {
    this.contentType = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    this.takeAllStudentCoursesWithGrades();
   }

  ngOnInit(): void {
  }

  takeAllStudentCoursesWithGrades() {
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
  }

  isStudent() {
    return localStorage.getItem('roles') === 'STUDENT';
  }

}
