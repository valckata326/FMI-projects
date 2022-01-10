import {RegisterUserModel} from "./RegisterUserModel";

export class RegisterStudentModel extends RegisterUserModel {
  constructor(public username: string,
              public password: string,
              public name: string,
              public age: number) {
    super(username, password, name);
  }
}
