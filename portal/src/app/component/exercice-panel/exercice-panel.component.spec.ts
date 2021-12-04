import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExercicePanelComponent } from './exercice-panel.component';

describe('ExercicePanelComponent', () => {
  let component: ExercicePanelComponent;
  let fixture: ComponentFixture<ExercicePanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExercicePanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExercicePanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
