import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";


@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  constructor(public router: Router) { }

  ngOnInit(): void {
  }

  signIn() {
    this.router.navigateByUrl("");
  }

  navigateToRegisterStudent() {
    this.router.navigateByUrl("register/student");
  }

  navigateToRegisterTeacher() {
    this.router.navigateByUrl("register/teacher");
  }
}
