import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-student-average-grade',
  templateUrl: './student-average-grade.component.html',
  styleUrls: ['./student-average-grade.component.css']
})
export class StudentAverageGradeComponent implements OnInit {
  average_grade: any;
  contentType;
  GRADE_URL = "http://localhost:8080/student/averageGradeOf/";
  STUDENT_URL = "http://localhost:8080/student/";
  studentName: any;

  constructor(public httpClient: HttpClient) {
    this.contentType = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    if(this.isStudent()) {
      this.takeGrade();
      this.takeStudentName();
    }

  }

  ngOnInit(): void {
  }

  isStudent() {
    if (localStorage.getItem('roles') === 'STUDENT') {
      return true;
    }
    return false;
  }

  takeGrade() {
    this.GRADE_URL += localStorage.getItem('id');
    let obj: any = {'grade': null};
    let grade = this.httpClient.get(this.GRADE_URL, {headers: this.contentType})
      .subscribe(response => {
        obj = response;
        this.average_grade = obj.grade.toFixed(2);
      });
  }
  takeStudentName() {
    this.STUDENT_URL += localStorage.getItem('id');
    let obj: any = {
      'username': null,
      'name': null,
      'age': null
    };
    let studentInfo = this.httpClient.get(this.STUDENT_URL, {headers: this.contentType})
      .subscribe(response => {
        obj = response;
        console.log(response);
        this.studentName = obj.name;
      });
    console.log(this.studentName);
  }
}
