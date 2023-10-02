import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotesModuleComponent } from './notes-module.component';

describe('NotesModuleComponent', () => {
  let component: NotesModuleComponent;
  let fixture: ComponentFixture<NotesModuleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NotesModuleComponent]
    });
    fixture = TestBed.createComponent(NotesModuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
