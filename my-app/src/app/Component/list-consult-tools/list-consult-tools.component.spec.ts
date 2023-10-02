import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListConsultToolsComponent } from './list-consult-tools.component';

describe('ListConsultToolsComponent', () => {
  let component: ListConsultToolsComponent;
  let fixture: ComponentFixture<ListConsultToolsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListConsultToolsComponent]
    });
    fixture = TestBed.createComponent(ListConsultToolsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
