import {CourseModel} from "../../models/CourseModel";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable()
export class CourseService {
  private contentType: HttpHeaders;
  constructor(private httpClient: HttpClient) {
    this.contentType = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  }
  takeAllCourses(_courses: CourseModel[], url:string): void {
    this.httpClient.get(url, {headers: this.contentType})
      .subscribe(response => {
        const entries = Object.entries(response);
        entries.forEach(entry => {
          let model: CourseModel;
          model = entry["1"];
          _courses.push(model);
        })
      });
  }
}
