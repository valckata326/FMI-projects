import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AddCourseModel} from "../../models/AddCourseModel";
import {FormGroup, FormControl, Validators, FormBuilder} from '@angular/forms';
import {stringify} from "@angular/compiler/src/util";

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {
  SERVER_URL = "http://localhost:8080/admin/addCourse";
  addCourseForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private httpClient: HttpClient) {
    this.addCourseForm = this.formBuilder.group({
      'courseName': ['', Validators.required],
      'totalHours': ['', Validators.required]
  });}

  ngOnInit(): void {
  }

  addCourse() {
      const headers = new HttpHeaders().set('Content-Type', 'application/json');
      let courseModel: AddCourseModel = this.addCourseForm.value;
      return this.httpClient.post(this.SERVER_URL, JSON.stringify(courseModel), {headers})
        .subscribe(response => {
            // @ts-ignore
            windows.location.reload();
          },
          (err) => {
            // @ts-ignore
            document.getElementById(`error_message`).style.display = "block";
          })
    }

}
