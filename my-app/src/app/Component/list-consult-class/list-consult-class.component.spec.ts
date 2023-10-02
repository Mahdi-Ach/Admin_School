import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListConsultClassComponent } from './list-consult-class.component';

describe('ListConsultClassComponent', () => {
  let component: ListConsultClassComponent;
  let fixture: ComponentFixture<ListConsultClassComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListConsultClassComponent]
    });
    fixture = TestBed.createComponent(ListConsultClassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
