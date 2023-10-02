import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListConsultModuleComponent } from './list-consult-module.component';

describe('ListConsultModuleComponent', () => {
  let component: ListConsultModuleComponent;
  let fixture: ComponentFixture<ListConsultModuleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListConsultModuleComponent]
    });
    fixture = TestBed.createComponent(ListConsultModuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
