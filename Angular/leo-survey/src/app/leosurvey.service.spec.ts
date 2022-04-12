import { TestBed } from '@angular/core/testing';

import { LeosurveyService } from './leosurvey.service';

describe('QuestionService', () => {
  let service: LeosurveyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LeosurveyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
