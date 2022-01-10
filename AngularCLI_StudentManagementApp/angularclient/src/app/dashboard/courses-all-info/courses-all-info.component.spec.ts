import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursesAllInfoComponent } from './courses-all-info.component';

describe('CoursesAllInfoComponent', () => {
  let component: CoursesAllInfoComponent;
  let fixture: ComponentFixture<CoursesAllInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoursesAllInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoursesAllInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
