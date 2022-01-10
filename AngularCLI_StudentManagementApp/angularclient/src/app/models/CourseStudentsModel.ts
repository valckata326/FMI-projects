import {StudentGradeModel} from "./StudentGradeModel";

export class CourseStudentsModel {
  constructor(public courseName: string,
              public studentList: Array<StudentGradeModel>,
              public averageGrade: number) {
  }
}
