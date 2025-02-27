import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { City } from '../../model/city.model';
import { Hotel } from '../../model/hotel.model';

@Component({
  selector: 'app-hotel-detail',
  templateUrl: './hotel-detail.component.html',
  styleUrl: './hotel-detail.component.scss'
})
export class HotelDetailComponent implements OnInit {
  
    myForm!: FormGroup;
    hotel!: Hotel;
    error!: null;
    status: boolean = false;
    cities: City[] = [];
    listHotels!: Hotel[] | undefined;
  listCities!: City[] |undefined;
  hotels: any[] = [];
  filteredHotels: Hotel[] | undefined;
  city!: City;
  
    selectedFile: File | null = null;
    imagePreview: string | ArrayBuffer | null = null;
  
  
  constructor(private formBuilder: FormBuilder, private apiService: ApiService, private router:Router, private route: ActivatedRoute){}
  
  ngOnInit(): void {
    let id = this.route.snapshot.params['id'];
    console.log("HotelId", id);
    this.apiService.getCities().subscribe({
      next: (data) => {
        this.cities = data;
      },
      error: (err) => this.error = err.message
    });
    if (id > 0) {
      this.status = true;
      this.apiService.getHotel(id).subscribe({
        next: (data) => {
          this.hotel = data;
          this.myForm.setValue({
            id: this.hotel.id,
            name: this.hotel.name,
            phone: this.hotel.phone,
            address: this.hotel.address,
            numberOfStar: this.hotel.numberOfStar,
            totalRoomOfAvailable: this.hotel.totalRoomOfAvailable,
            price: this.hotel.price,
            city: this.hotel.city.id
          });
          // Charger l'image existante
          if (this.hotel.imageUrl) {
            this.imagePreview = this.hotel.imageUrl;
          }
        },
        error: (err) => this.error = err.message
      });
    }
  }
  formSubmitted = false;
  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;

      // Prévisualisation de l'image
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result;
      };
      reader.readAsDataURL(file);
    }
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
  
  onUpdateHotelDetail(hotel: Hotel) {
    this.router.navigateByUrl('hotel-detail/' + hotel.id);
  }
  onUpdateHotel(hotel: Hotel) {
    this.router.navigateByUrl('hotel/' + hotel.id);
  }
  onDeleteHotel(hotel: Hotel) {
    if (confirm("vous êtes sur de vouloir supprimer cette Hotel")) {
      this.apiService.delHotel(hotel).subscribe({
        next: (data) => {
          console.log("Hotel supprimé", data);
          this.getAllHotels();
          this.router.navigateByUrl('hotels');
        },
        error: (err) => {
          console.error("Erreur lors de la suppression: ", err);
          this.error = err.message;
        },
        complete: () => console.log("Requete terminée")
      }
    );}
  }
  

}


