import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl, Validators, FormBuilder} from '@angular/forms'
import {Router} from "@angular/router";
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {LoginUserModel} from "../models/LoginUserModel";
import {AuthService} from "../_services/auth.service";
import {stringify} from "@angular/compiler/src/util";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  SERVER_URL = "http://localhost:8080/api/auth/signin";
  loginForm: FormGroup;
  isSuccessfulLogin: number = 1;
  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private httpClient: HttpClient,
              private router: Router) {
    this.loginForm = this.formBuilder.group({
      'username': ['', Validators.required],
      'password': ['', Validators.required]
    });
  }

  ngOnInit() {

  }

  login() {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    let loginUser: LoginUserModel = this.loginForm.value;
    return this.httpClient.post(this.SERVER_URL, JSON.stringify(loginUser), {headers})
      .subscribe(response => {
          // @ts-ignore
          this.authService.saveToken(response.token, response.roles, response.id);
          this.isSuccessfulLogin = 0;
          location.assign("/dashboard");
        },
        (err) => {
          // @ts-ignore
          document.getElementById(`error_message`).style.display = "block";
        })
  }

  navigateToRegister() {
    this.router.navigateByUrl("/register/user");
  }
}
