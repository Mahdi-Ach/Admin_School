import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListConsultStudentComponent } from './list-consult-student.component';

describe('ListConsultStudentComponent', () => {
  let component: ListConsultStudentComponent;
  let fixture: ComponentFixture<ListConsultStudentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListConsultStudentComponent]
    });
    fixture = TestBed.createComponent(ListConsultStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
