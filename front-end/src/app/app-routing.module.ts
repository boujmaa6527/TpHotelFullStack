import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HotelsComponent } from './components/hotels/hotels.component';
import { HotelComponent } from './components/hotel/hotel.component';
import { LoginComponent } from './components/login/login.component';
import { HotelDetailComponent } from './components/hotel-detail/hotel-detail.component';

const routes: Routes = [

    {path: "hotels", component: HotelsComponent},
    {path: "hotel/:id", component: HotelComponent},
    {path: "hotel-detail", component:HotelDetailComponent},
    {path: "hotel-detail/:id", component:HotelDetailComponent},
    {path: "hotel", component: HotelComponent},
    {path: "login", component: LoginComponent},
    {path: "", component: HotelsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
