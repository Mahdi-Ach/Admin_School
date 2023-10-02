import { TestBed } from '@angular/core/testing';

import { CrudStudentService } from './crud-student.service';

describe('CrudStudentService', () => {
  let service: CrudStudentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CrudStudentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
