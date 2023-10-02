import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SinginTeacherComponent } from './singin-teacher.component';

describe('SinginTeacherComponent', () => {
  let component: SinginTeacherComponent;
  let fixture: ComponentFixture<SinginTeacherComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SinginTeacherComponent]
    });
    fixture = TestBed.createComponent(SinginTeacherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
