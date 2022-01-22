import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";

import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http"

import {ReactiveFormsModule} from "@angular/forms";
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';
import {RegisterComponent} from './register/register.component'
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardStudentComponent } from './board-student/board-student.component';
import { BoardTeacherComponent } from './board-teacher/board-teacher.component';
import { UserRegisterComponent } from './register/user/user-register/user-register.component';
import { TeacherRegisterComponent } from './register/user/teacher-register/teacher-register.component';
import { StudentRegisterComponent } from './register/user/student-register/student-register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { StudentAverageGradeComponent } from './dashboard/student-average-grade/student-average-grade.component';
import { CoursesTeacherTableComponent } from './dashboard/courses-teacher-table/courses-teacher-table.component';
import {AuthInterceptor} from "./_helpers/auth.interceptor";
import { TeacherCoursesComponent } from './dashboard/teacher-courses/teacher-courses.component';
import { CoursesAllInfoComponent } from './dashboard/courses-all-info/courses-all-info.component';
import { CoursePageComponent } from './course/course-page/course-page.component';
import { CourseTeacherComponent } from './course/course-teacher/course-teacher.component';
import { CourseStudentsComponent } from './course/course-students/course-students.component';
import { AddStudentToCourseComponent } from './course/add-student-to-course/add-student-to-course.component';
import { AddStudentGradeToCourseComponent } from './course/add-student-grade-to-course/add-student-grade-to-course.component';
import { AddTeacherToCourseComponent } from './course/add-teacher-to-course/add-teacher-to-course.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { AllUsersComponent } from './admin-page/all-users/all-users.component';
import { AddCourseComponent } from './admin-page/add-course/add-course.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardUserComponent,
    BoardStudentComponent,
    BoardTeacherComponent,
    UserRegisterComponent,
    TeacherRegisterComponent,
    StudentRegisterComponent,
    DashboardComponent,
    NavBarComponent,
    StudentAverageGradeComponent,
    CoursesTeacherTableComponent,
    TeacherCoursesComponent,
    CoursesAllInfoComponent,
    CoursePageComponent,
    CourseTeacherComponent,
    CourseStudentsComponent,
    AddStudentToCourseComponent,
    AddStudentGradeToCourseComponent,
    AddTeacherToCourseComponent,
    AdminPageComponent,
    AllUsersComponent,
    AddCourseComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatInputModule,
    ReactiveFormsModule,
    AppRoutingModule,
    MatIconModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
