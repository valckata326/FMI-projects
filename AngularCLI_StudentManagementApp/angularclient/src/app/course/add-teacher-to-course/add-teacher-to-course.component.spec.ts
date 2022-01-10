import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTeacherToCourseComponent } from './add-teacher-to-course.component';

describe('AddTeacherToCourseComponent', () => {
  let component: AddTeacherToCourseComponent;
  let fixture: ComponentFixture<AddTeacherToCourseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddTeacherToCourseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTeacherToCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
