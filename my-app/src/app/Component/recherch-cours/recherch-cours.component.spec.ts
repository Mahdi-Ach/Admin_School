import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecherchCoursComponent } from './recherch-cours.component';

describe('RecherchCoursComponent', () => {
  let component: RecherchCoursComponent;
  let fixture: ComponentFixture<RecherchCoursComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RecherchCoursComponent]
    });
    fixture = TestBed.createComponent(RecherchCoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
