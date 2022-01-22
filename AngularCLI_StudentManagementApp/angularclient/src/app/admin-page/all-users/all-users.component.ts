import { Component, OnInit } from '@angular/core';
import {AdminService} from "../../_services/management/AdminService";
import {UserDTO} from "../../models/UserDTO";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {
  private adminService: AdminService = new AdminService(this.httpClient);
  public _users: UserDTO[] = [];
  private GET_ALL_USERS: string = "http://localhost:8080/admin/getAllUsers"
  private AUTHORIZE_USER_URL: string = "http://localhost:8080/admin/authorize/"

  constructor(private httpClient: HttpClient) {
    this.takeAllUsers();
  }

  takeAllUsers() {
    this.adminService.takeAllUsers(this._users, this.GET_ALL_USERS);
  }

  ngOnInit(): void {
  }

  confirmUser(username: string) {
    let URL = this.AUTHORIZE_USER_URL + username;
    this.adminService.authorizeUser(username, URL);
  }

  isUser(roles: string): boolean {
    return roles === 'USER ';
  }
}
