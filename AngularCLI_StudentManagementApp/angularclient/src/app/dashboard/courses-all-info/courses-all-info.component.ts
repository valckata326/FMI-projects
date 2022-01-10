import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CourseAverageGradeTeacherModel} from "../../models/CourseAverageGradeTeacherModel";

@Component({
  selector: 'app-courses-all-info',
  templateUrl: './courses-all-info.component.html',
  styleUrls: ['./courses-all-info.component.css']
})
export class CoursesAllInfoComponent implements OnInit {
  courses: Array<any> = [];
  SERVER_URL = 'http://localhost:8080/admin/getAllCoursesInformation'
  private contentType: HttpHeaders;

  constructor(public httpClient: HttpClient) {
    this.contentType = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    this.takeAllCoursesInformation();
  }

  ngOnInit(): void {
  }

  isAdmin() {
    return localStorage.getItem('roles') === 'ADMIN';
  }
  takeAllCoursesInformation() {
    this.httpClient.get(this.SERVER_URL, {headers: this.contentType})
      .subscribe(response => {
        const entries = Object.entries(response);
        entries.forEach(entry => {
          let model: CourseAverageGradeTeacherModel;
          model = entry["1"];
          this.courses.push(model);
          //console.log(model);
        })
      });
  }
}
