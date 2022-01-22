import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {RegisterTeacherModel} from "../../../models/RegisterTeacherModel";

@Component({
  selector: 'app-teacher-register',
  templateUrl: './teacher-register.component.html',
  styleUrls: ['./teacher-register.component.css']
})
export class TeacherRegisterComponent implements OnInit {
  SERVER_URL = "http://localhost:8080/api/auth/signup/teacher"
  registerForm: FormGroup;

  constructor(public router: Router,
              private formBuilder: FormBuilder,
              private httpClient: HttpClient) {
    this.registerForm = this.formBuilder.group({
      'username': ['', Validators.required],
      'password': ['', Validators.required, Validators.minLength(5), Validators.maxLength(15)],
      'confirm_password': ['', Validators.required, Validators.minLength(5), Validators.maxLength(15)],
      'name': ['', Validators.required],
      'degree': ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  navigateToRegisterStudent() {
    this.router.navigateByUrl("register/student");
  }

  navigateToRegisterUser() {
    this.router.navigateByUrl("register/user");
  }

  signIn() {
    this.router.navigateByUrl("");
  }

  registerTeacher() {
    if (this.registerForm.errors != null) {
      // @ts-ignore
      document.getElementById(`error_message`).style.display = "block";
      return;
    }
    let registerUser: RegisterTeacherModel = this.registerForm.value;
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.post(this.SERVER_URL, JSON.stringify(registerUser), {headers})
      .subscribe(() => {
          this.router.navigateByUrl('')
        },
        () => {
          // @ts-ignore
          document.getElementById(`error_message`).style.display = "block";
        })
  }

  checkIfMatchingPasswords(passwordKey: string, passwordConfirmationKey: string) {
    return (group: FormGroup) => {
      let passwordInput = group.controls[passwordKey],
          passwordConfirmationInput = group.controls[passwordConfirmationKey];
      if (passwordInput.value !== passwordConfirmationInput.value) {
        return passwordConfirmationInput.setErrors({notEquivalent: true})
      }
      else {
          return passwordConfirmationInput.setErrors(null);
      }
    }
  }
}
