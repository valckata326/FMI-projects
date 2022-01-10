import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherRegisterComponent } from './teacher-register.component';

describe('TeacherRegisterComponent', () => {
  let component: TeacherRegisterComponent;
  let fixture: ComponentFixture<TeacherRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeacherRegisterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
