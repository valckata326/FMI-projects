import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CourseAverageGradeTeacherModel} from "../../models/CourseAverageGradeTeacherModel";
import {CourseTeacherModel} from "../../models/CourseTeacherModel";

@Component({
  selector: 'app-course-teacher',
  templateUrl: './course-teacher.component.html',
  styleUrls: ['./course-teacher.component.css']
})
export class CourseTeacherComponent implements OnInit {
  coursesWithTeacher: Array<any> = [];
  SERVER_URL = 'http://localhost:8080/course/getCoursesWithTheirTeacher';
  private contentType: HttpHeaders;
  constructor(public httpClient: HttpClient) {
    this.contentType = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    this.takeAllCoursesWithTheirTeacher()
  }

  ngOnInit(): void {
  }
  takeAllCoursesWithTheirTeacher() {
    this.httpClient.get(this.SERVER_URL, {headers: this.contentType})
      .subscribe(response => {
        const entries = Object.entries(response);
        entries.forEach(entry => {
          let model: CourseTeacherModel;
          model = entry["1"];
          this.coursesWithTeacher.push(model);
        })
      });
  }
}
