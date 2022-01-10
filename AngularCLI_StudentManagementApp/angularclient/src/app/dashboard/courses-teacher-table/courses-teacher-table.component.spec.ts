import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursesTeacherTableComponent } from './courses-teacher-table.component';

describe('CoursesTeacherTableComponent', () => {
  let component: CoursesTeacherTableComponent;
  let fixture: ComponentFixture<CoursesTeacherTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoursesTeacherTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoursesTeacherTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
