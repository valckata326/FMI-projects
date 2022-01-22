import { Component, OnInit } from '@angular/core';
import {AdminService} from "../_services/management/AdminService";
import {UserDTO} from "../models/UserDTO";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  private adminService: AdminService = new AdminService(this.httpClient);
  private users: UserDTO[] = [];
  private GET_ALL_USERS: string = "http://localhost:8080/admin/getAllUsers"

  constructor(private httpClient: HttpClient) {
    this.takeAllUsers();
  }

  ngOnInit(): void {
  }

  takeAllUsers() {
    this.adminService.takeAllUsers(this.users, this.GET_ALL_USERS);
  }
}
