import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListConsultTeacherComponent } from './list-consult-teacher.component';

describe('ListConsultTeacherComponent', () => {
  let component: ListConsultTeacherComponent;
  let fixture: ComponentFixture<ListConsultTeacherComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListConsultTeacherComponent]
    });
    fixture = TestBed.createComponent(ListConsultTeacherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
