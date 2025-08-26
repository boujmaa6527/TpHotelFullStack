import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelsComponent } from './hotels.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';


describe('HotelsComponent', () => {
  let component: HotelsComponent;
  let fixture: ComponentFixture<HotelsComponent>;
  let hotelServiceSpy: {getHotels: jasmine.Spy};
  let HttpClientSpy: { get: jasmine.Spy};

  beforeEach(async () => {
    HttpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);
    hotelServiceSpy = jasmine.createSpyObj('HotelService', ['getAllHotels']);



    await TestBed.configureTestingModule({
      declarations: [HotelsComponent],
      imports: [HttpClientModule],
      providers: [{ provide: HttpClient, useValue:HttpClientSpy}]
    }).compileComponents();
    
    fixture = TestBed.createComponent(HotelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
