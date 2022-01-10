import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentAverageGradeComponent } from './student-average-grade.component';

describe('StudentAverageGradeComponent', () => {
  let component: StudentAverageGradeComponent;
  let fixture: ComponentFixture<StudentAverageGradeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentAverageGradeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentAverageGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
