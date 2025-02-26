import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Hotel } from '../../model/hotel.model';
import { ApiService } from '../../services/api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { City } from '../../model/city.model';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrl: './hotel.component.scss'
})
export class HotelComponent  implements OnInit{
  myForm: FormGroup;
  hotel: Hotel;
  error: string = "";
  status: boolean = false;
  cities: City[] = [];
  
  selectedFile: File | null = null;
  imagePreview: string | ArrayBuffer | null = null;
  
  constructor(private formBuilder: FormBuilder, private apiService: ApiService, private router:Router, private route: ActivatedRoute){
    this.hotel = new Hotel(0, "", "", "", "", 0, 0,0,new City(0, "",""));
    this.myForm = this.formBuilder.group({
      id: [this.hotel.id],
      name: ["", Validators.required], 
      address: ["", Validators.required],
      phone: ["", Validators.required],
      numberOfStar: ["", [Validators.required, Validators.min(0)]],
      totalRoomOfAvailable: ["", [Validators.required, Validators.min(0)]],
      price: ["", [Validators.required, Validators.min(0)]],
      city:["", Validators.required]

    });

  }

  ngOnInit(): void {
    let id = this.route.snapshot.params['id'];
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
  onAddHotel(form: FormGroup) {
    if (form.valid) {
      const cityId = form.value.city;
      if (!cityId) {
        this.error = 'La ville est obligatoire';
        return;
      }
      console.log("Form value", form.value);
      const formData = new FormData();
      formData.append('name', form.value.name);
      formData.append('phone', form.value.phone);
      formData.append('address', form.value.address);
      formData.append('numberOfStar', form.value.numberOfStar);
      formData.append('totalRoomOfAvailable', form.value.totalRoomOfAvailable);
      formData.append('price', form.value.price);
      formData.append('city', cityId);

      if (this.selectedFile) {
        formData.append('file', this.selectedFile);
      }
      console.log("Form data", formData);

      if (this.status) {
        this.updateHotel(formData);
      } else {
        this.apiService.postHotel(formData).subscribe({
          next: (response) => {
            console.log('Hotel created successfully:', response);
            this.router.navigateByUrl('hotels');
          },
          error: (err) => {
            console.error('Error during hotel creation:', err);
            this.error = err.message;
          },
        });
      }
    } else {
      this.error = 'Erreur de saisie!';
    }
  }


  /**
   * Méthode de mise à jour d'une nouvelle formation
   * @param form comprend le formulaire avec toutes les données saisies par l'utilisateur
   */
  updateHotel(formData: FormData) {
    this.apiService.updateHotel(this.myForm.value.id, formData).subscribe({
      next: () => {
        console.log('Hotel mise à jour avec succès');
        this.router.navigateByUrl('Hotels');
      },
      error: (err) => this.error = err.message
    });
  }

}
