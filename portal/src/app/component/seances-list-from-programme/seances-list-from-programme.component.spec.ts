import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeancesListFromProgrammeComponent } from './seances-list-from-programme.component';

describe('SeancesListFromProgrammeComponent', () => {
  let component: SeancesListFromProgrammeComponent;
  let fixture: ComponentFixture<SeancesListFromProgrammeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeancesListFromProgrammeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SeancesListFromProgrammeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
