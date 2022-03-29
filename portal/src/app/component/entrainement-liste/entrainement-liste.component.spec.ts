import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntrainementListeComponent } from './entrainement-liste.component';

describe('EntrainementListeComponent', () => {
  let component: EntrainementListeComponent;
  let fixture: ComponentFixture<EntrainementListeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntrainementListeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EntrainementListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
