import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Hotel } from '../model/hotel.model';
import { environment } from '../../environments/environment';
import { City } from '../model/city.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  public getHotels() {
    return this.http.get<Hotel[]>(environment.host + "/hotels");
  }

  public getHotel(id: number) {
    return this.http.get<Hotel>(environment.host + "/hotels/" + id);
  }

  public getCities() {
    return this.http.get<City[]>(environment.host + "/cities");
  }

  public getCity(id: number) {
    return this.http.get<City>(environment.host + "/cities/" + id);
  }
  public postHotel(formData: FormData): Observable<any>{
    return this.http.post<Hotel>(`${environment.host}/hotels`, formData);
  }
  public updateHotel(id: number, formData: FormData): Observable<any> {
    return this.http.put(`${environment.host}/hotels/${id}`, formData);
  }
  public delHotel(hotel: Hotel) {
    return this.http.delete(environment.host + "/hotels/" + hotel.id);
  }

  public putHotel(hotel: any) {
    return this.http.put<Hotel>(environment.host + "/hotels/" + hotel.id, hotel);
  }

}
