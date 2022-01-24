import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentCoursesGradesComponent } from './student-courses-grades.component';

describe('StudentCoursesGradesComponent', () => {
  let component: StudentCoursesGradesComponent;
  let fixture: ComponentFixture<StudentCoursesGradesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentCoursesGradesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentCoursesGradesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
