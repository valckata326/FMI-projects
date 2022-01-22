import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(public router: Router) { }

  ngOnInit(): void {
  }

  onHome() {
    location.assign("dashboard");
  }

  onCourse() {
    location.assign("course");
  }

  onLogout() {
    localStorage.clear();
    this.router.navigateByUrl("");
  }

  onAdmin() {
    location.assign("admin");
  }

  isAdmin(): boolean {
    return localStorage.getItem('roles') === 'ADMIN';
  }
}
