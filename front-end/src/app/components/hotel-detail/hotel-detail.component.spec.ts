import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelDetailComponent } from './hotel-detail.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { of , throwError} from 'rxjs';
import { Hotel } from '../../model/hotel.model';
import { City } from '../../model/city.model';

describe('HotelDetailComponent', () => {
  let component: HotelDetailComponent;
  let fixture: ComponentFixture<HotelDetailComponent>;
  let apiServiceSpy: jasmine.SpyObj<ApiService>

  beforeEach(() => {
   const spy = jasmine.createSpyObj('ApiService', ['getHotels']);

   TestBed.configureTestingModule({
        declarations: [HotelDetailComponent],
        providers: [{provide: ApiService, useValue:spy}]
   });
    
    fixture = TestBed.createComponent(HotelDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    apiServiceSpy = TestBed.inject(ApiService) as jasmine.SpyObj<ApiService>;
  });

  it('devrait récupérer et assigner les hotels correctement', () => {
 
    const mockHotel: Hotel[]=[{
      id: 1, name: "hotel test",
      phone: '',
      address: '',
      imageUrl: '',
      numberOfStar: 0,
      totalRoomOfAvailable: 0,
      price: 0,
      city: new City(1, "1234", "description")
    }];
    apiServiceSpy.getHotels.and.returnValue(of(mockHotel)); 

    component.getAllHotels();

    expect(component.listHotels).toEqual(mockHotel); 
    expect(component.filteredHotels).toEqual(mockHotel);
    expect(component.error).toBeNull();



    expect(component).toBeTruthy();
  });
});


