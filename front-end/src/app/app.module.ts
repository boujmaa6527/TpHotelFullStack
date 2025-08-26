import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HotelsComponent } from './components/hotels/hotels.component';
import { HttpClientModule } from '@angular/common/http';
import { MenuComponent } from './menu/menu.component';
import { HotelComponent } from './components/hotel/hotel.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { HotelDetailComponent } from './components/hotel-detail/hotel-detail.component';
import { AdminComponent } from './components/admin/admin.component';
import { VilleDetailComponent } from './components/ville-detail/ville-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    HotelsComponent,
    MenuComponent,
    HotelComponent,
    LoginComponent,
    HotelDetailComponent,
    AdminComponent,
    VilleDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
