import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VilleDetailComponent } from './ville-detail.component';

describe('VilleDetailComponent', () => {
  let component: VilleDetailComponent;
  let fixture: ComponentFixture<VilleDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VilleDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VilleDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
