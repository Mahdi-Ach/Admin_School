import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SinginStudentComponent } from './singin-student.component';

describe('SinginStudentComponent', () => {
  let component: SinginStudentComponent;
  let fixture: ComponentFixture<SinginStudentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SinginStudentComponent]
    });
    fixture = TestBed.createComponent(SinginStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
