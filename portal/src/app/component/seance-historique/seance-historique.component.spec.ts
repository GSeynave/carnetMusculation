import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeanceHistoriqueComponent } from './seance-historique.component';

describe('SeanceHistoriqueComponent', () => {
  let component: SeanceHistoriqueComponent;
  let fixture: ComponentFixture<SeanceHistoriqueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeanceHistoriqueComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SeanceHistoriqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
