import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeachersInfoComponent } from './teachers-info.component';

describe('TeachersInfoComponent', () => {
  let component: TeachersInfoComponent;
  let fixture: ComponentFixture<TeachersInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TeachersInfoComponent]
    });
    fixture = TestBed.createComponent(TeachersInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
