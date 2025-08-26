import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { City } from '../../model/city.model';
import { Hotel } from '../../model/hotel.model';
import { AuthenticateService } from '../../services/authenticate.service';
import { User } from '../../model/user.model';

@Component({
  selector: 'app-hotel-detail',
  templateUrl: './hotel-detail.component.html',
  styleUrl: './hotel-detail.component.scss'
})
export class HotelDetailComponent implements OnInit {

  myForm!: FormGroup;
  hotel: any;
  error!: null;
  status: boolean = false;
  cities: City[] = [];
  listHotels!: Hotel[] | undefined;
  listCities!: City[] | undefined;
  hotels: any[] = [];
  filteredHotels: Hotel[] | undefined;
  city!: City;
  isAdmin: boolean = false;
  user!: User;
  userString!: string;
  selectedFile: File | null = null;
  imagePreview: string | ArrayBuffer | null = null;


  constructor(private formBuilder: FormBuilder, private apiService: ApiService, private router: Router, private route: ActivatedRoute, public authService: AuthenticateService) {
    //check if user is admin when if he is connected
    console.log("UerRole:" , this.authService.getUser().role);
    this.userString = this.authService.getUser().role.rolename;
    console.log("UserString: ", this.userString);
     this.isAdmin = this.authService.isAdmin();
    console.log("isADMIN: " , this.isAdmin);
    const isAdmin2 = Object.keys(this.authService.getUser().role).includes('ADMIN');
    
    
    
  }

  ngOnInit(): void {
   
    console.log("IsAdmin", this.isAdmin);
    let id = this.route.snapshot.params['id'];
    console.log("HotelId", id);
    this.isAdmin = this.authService.isAdmin();
    console.log("admin :", this.isAdmin);
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
          // Charger l'image existante
          if (this.hotel.imageUrl) {
            this.imagePreview = this.hotel.imageUrl;
          }
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
      );
    }
  }
  OnIsAdmin(): boolean {
    return this.authService.getUser().role.rolename == "ADMIN";
    
  }


}


