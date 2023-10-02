import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListConsultFilierComponent } from './list-consult-filier.component';

describe('ListConsultFilierComponent', () => {
  let component: ListConsultFilierComponent;
  let fixture: ComponentFixture<ListConsultFilierComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListConsultFilierComponent]
    });
    fixture = TestBed.createComponent(ListConsultFilierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
