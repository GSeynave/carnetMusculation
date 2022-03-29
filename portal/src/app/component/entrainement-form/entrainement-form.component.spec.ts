import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntrainementFormComponent } from './entrainement-form.component';

describe('EntrainementFormComponent', () => {
  let component: EntrainementFormComponent;
  let fixture: ComponentFixture<EntrainementFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntrainementFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EntrainementFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
