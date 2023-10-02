import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElementInfoComponent } from './element-info.component';

describe('ElementInfoComponent', () => {
  let component: ElementInfoComponent;
  let fixture: ComponentFixture<ElementInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ElementInfoComponent]
    });
    fixture = TestBed.createComponent(ElementInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
