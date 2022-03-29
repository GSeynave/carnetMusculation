import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntrainementStartComponent } from './entrainement-start.component';

describe('EntrainementStartComponent', () => {
  let component: EntrainementStartComponent;
  let fixture: ComponentFixture<EntrainementStartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntrainementStartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EntrainementStartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
