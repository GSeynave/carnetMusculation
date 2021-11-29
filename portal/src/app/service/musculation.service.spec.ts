import { TestBed } from '@angular/core/testing';

import { MusculationService } from './musculation.service';

describe('MusculationService', () => {
  let service: MusculationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MusculationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
