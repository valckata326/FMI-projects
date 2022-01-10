import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardStudentComponent } from './board-student.component';

describe('BoardStudentComponent', () => {
  let component: BoardStudentComponent;
  let fixture: ComponentFixture<BoardStudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoardStudentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoardStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
