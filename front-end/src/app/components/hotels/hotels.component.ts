import { Component, OnInit } from '@angular/core';
import { Hotel } from '../../model/hotel.model';
import { City } from '../../model/city.model';
import { ApiService } from '../../services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrl: './hotels.component.scss'
})
export class HotelsComponent  implements OnInit{

  
  listHotels!: Hotel[] | undefined;
  listCities!: City[] |undefined;
  error = null; 
  hotels: any[] = [];
  cities: any = {};
  filteredHotels: Hotel[] | undefined;
  city!: City;
  
  constructor(private apiService: ApiService, private router: Router){}
  
  ngOnInit(): void {

    this.getAllHotels();
    this.getAllCities();
    this.filteredHotels = this.listHotels;
    
  }
  getAllHotels() {
    this.apiService.getHotels().subscribe({
      next: (data) => {
        console.log("Hotels data:", data);
        this.listHotels = data;
        this.filteredHotels = data;
      },
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
  }

  getAllCities(){
    this.apiService.getCities().subscribe({
      next: (data) => {
        console.log("City Data:" , data);
        this.listCities = data;
      },
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
  }
  filterByCity(cityId: number): void {
    if (this.listHotels) {
      this.filteredHotels = this.listHotels.filter(
        (hotel) => hotel.city && hotel.city.id === cityId
      );
      console.log("Filtered Hotels data:", this.filteredHotels);
    }
  }
  resetFilter(): void {
    this.filteredHotels = this.listHotels;
  }
  
  onCityChange(event: any): void{

    const selectedCityId = event.target.value;
    if(selectedCityId){
      this.filterByCity(Number(selectedCityId));
    }else{
      this.resetFilter();
    }
    
 
  }
}
