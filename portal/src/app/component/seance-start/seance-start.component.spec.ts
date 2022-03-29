import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeanceStartComponent } from './seance-start.component';

describe('SeanceStartComponent', () => {
  let component: SeanceStartComponent;
  let fixture: ComponentFixture<SeanceStartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeanceStartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SeanceStartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
