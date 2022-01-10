import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {RegisterStudentModel} from "../../../models/RegisterStudentModel";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../_services/auth.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-student-register',
  templateUrl: './student-register.component.html',
  styleUrls: ['./student-register.component.css']
})
export class StudentRegisterComponent implements OnInit {

  registerForm: FormGroup;
  SERVER_URL = "http://localhost:8080/api/auth/signup/student"
  isSuccessfulLogin: number = 1;
  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private httpClient: HttpClient,
              private router: Router) {
    this.registerForm = this.formBuilder.group({
      'username': ['', Validators.required],
      'password': ['', Validators.required],
      'name': ['', Validators.required],
      'age': ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  navigateToRegisterTeacher() {
    this.router.navigateByUrl("register/teacher");
  }

  navigateToRegisterUser() {
    this.router.navigateByUrl("register/user");
  }

  signIn() {
    this.router.navigateByUrl("");
  }

  registerStudent() {
    let registerUser: RegisterStudentModel = this.registerForm.value;
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.post(this.SERVER_URL, JSON.stringify(registerUser), {headers})
      .subscribe(response => {
          console.log(response);
          console.log(registerUser);
          this.router.navigateByUrl('')
        },
        (err) => {
          // @ts-ignore
          document.getElementById(`error_message`).style.display = "block";
        })
  }
}
