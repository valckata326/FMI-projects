import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddStudentGradeToCourseComponent } from './add-student-grade-to-course.component';

describe('AddStudentGradeToCourseComponent', () => {
  let component: AddStudentGradeToCourseComponent;
  let fixture: ComponentFixture<AddStudentGradeToCourseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddStudentGradeToCourseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddStudentGradeToCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
