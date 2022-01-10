import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentRegisterComponent } from './student-register.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

describe('StudentRegisterComponent', () => {
  let component: StudentRegisterComponent;
  let fixture: ComponentFixture<StudentRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentRegisterComponent ],
      imports: [ReactiveFormsModule, FormsModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should be invalid when empty', () => {
    expect(component.registerForm.valid).toBeFalse();
  })

  it('username field validity', () => {
    let username = component.registerForm.controls['username'];
    expect(username.valid).toBeFalse();
  })

  it('password field validity', () => {
    let password = component.registerForm.controls['password'];
    expect(password.valid).toBeFalse();
  })

  it('name field validity', () => {
    let name = component.registerForm.controls['name'];
    expect(name.valid).toBeFalse();
  })

  it('age field validity', () => {
    let age = component.registerForm.controls['age'];
    expect(age.valid).toBeFalse();
  })

  it('submitting a form that registers student', () => {
    expect(component.registerForm.valid).toBeFalse();

    component.registerForm.controls['username'].setValue('Value');
    component.registerForm.controls['password'].setValue('some password');
    component.registerForm.controls['name'].setValue('Valentin');
    component.registerForm.controls['age'].setValue('22');

    expect(component.registerForm.valid).toBeTruthy();

    component.registerForm;
  })
});
