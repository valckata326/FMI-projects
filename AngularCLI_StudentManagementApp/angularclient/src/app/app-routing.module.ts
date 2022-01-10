import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {UserRegisterComponent} from "./register/user/user-register/user-register.component";
import {TeacherRegisterComponent} from "./register/user/teacher-register/teacher-register.component";
import {StudentRegisterComponent} from "./register/user/student-register/student-register.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {AuthGuardService} from "./_services/auth.guard.service";
import {CoursePageComponent} from "./course/course-page/course-page.component";

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'register/user',
    component: UserRegisterComponent
  },
  {
    path: 'register/teacher',
    component: TeacherRegisterComponent
  },
  {
    path: 'register/student',
    component: StudentRegisterComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent, canActivate: [AuthGuardService]

  },
  {
    path: 'course',
    component: CoursePageComponent, canActivate: [AuthGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
