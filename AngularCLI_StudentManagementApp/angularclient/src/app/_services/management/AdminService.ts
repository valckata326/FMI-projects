import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserDTO} from "../../models/UserDTO";

export class AdminService {
  private contentType: HttpHeaders;
  constructor(private httpClient: HttpClient) {
    this.contentType = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  }

  takeAllUsers(users: UserDTO[], url:string): void {
    this.httpClient.get(url, {headers: this.contentType})
      .subscribe(response => {
        const entries = Object.entries(response);
        entries.forEach(entry => {
          let model: UserDTO;
          model = entry["1"];
          users.push(model);
        })
      });
  }

  authorizeUser(username: string, URL: string) {
    this.httpClient.get(URL, {headers: this.contentType})
      .subscribe(response => {
        console.log(response);
        window.location.reload();
      });
  }
}
