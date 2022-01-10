import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AddStudentToCourseComponent} from './add-student-to-course.component';
import {CourseModel} from "../../models/CourseModel";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {Type} from "@angular/core";
import {CourseService} from "../../_services/management/CourseService";
import {Observable} from "rxjs";
import {HttpClientModule} from "@angular/common/http";

describe('AddStudentToCourseComponent', () => {
  let component: AddStudentToCourseComponent;
  let fixture: ComponentFixture<AddStudentToCourseComponent>;
  let spy: any;
  let httpMock: HttpTestingController;
  let courses: CourseModel[] = [];
  let courseServiceMock: CourseService;
  describe('AddStudentToCourseComponent', () => {
    beforeEach(async () => {
      await TestBed.configureTestingModule({
        providers: [
          {
            provide: CourseService, useValue: jasmine.createSpyObj('CourseService', ['takeAllCourses'])
          }
        ],
        imports: [HttpClientTestingModule, HttpClientModule],
        declarations: [
          AddStudentToCourseComponent,
        ]
      }).compileComponents();
      courseServiceMock = TestBed.get(CourseService);
      httpMock = fixture.debugElement.injector
        .get<HttpTestingController>(HttpTestingController as Type<HttpTestingController>);
      courses = [];
      courses.push(new CourseModel(1, "Algebra"));
    })
  })

  afterEach(() => {
    httpMock.verify();
  });
  beforeEach(async () => {
    fixture = TestBed.createComponent(AddStudentToCourseComponent);
    component = fixture.componentInstance;
    // @ts-ignore
    spyOn(component, 'takeAllCourses').and.callFake(() => {
      return courses;
    })
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should take all courses', () => {
    (courseServiceMock.takeAllCourses as jasmine.Spy).and.callFake(function (): Observable<Object> {
      courses.push(new CourseModel(2, "diskretni"));
      return new Observable<Object>();
    })

    expect(courses.length).toEqual(1);
    component.takeAllCourses();
    const dummyCourse: CourseModel = new CourseModel(3, 'dis');
    const req = httpMock.expectOne(component.GET_COURSES_NAME_AND_IDS);
    req.flush(dummyCourse);
    expect(courseServiceMock.takeAllCourses(courses, component.GET_COURSES_NAME_AND_IDS)).toHaveBeenCalled();
    expect(courses.length).toEqual(2);
  })
});
