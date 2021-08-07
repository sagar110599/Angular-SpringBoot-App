import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnauthAccessComponent } from './unauth-access.component';

describe('UnauthAccessComponent', () => {
  let component: UnauthAccessComponent;
  let fixture: ComponentFixture<UnauthAccessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnauthAccessComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnauthAccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
