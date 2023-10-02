import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilierInfoComponent } from './filier-info.component';

describe('FilierInfoComponent', () => {
  let component: FilierInfoComponent;
  let fixture: ComponentFixture<FilierInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FilierInfoComponent]
    });
    fixture = TestBed.createComponent(FilierInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
