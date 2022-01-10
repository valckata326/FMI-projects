import {RegisterUserModel} from "./RegisterUserModel";
import {Degree} from "./Degree";

export class RegisterTeacherModel extends RegisterUserModel {
  constructor(public username: string,
              public password: string,
              public name: string,
              public degree: Degree) {
    super(username, password, name);
  }
}
