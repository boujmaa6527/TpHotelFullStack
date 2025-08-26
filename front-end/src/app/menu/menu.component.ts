import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { AuthenticateService } from '../services/authenticate.service';
import { User } from '../model/user.model';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent implements OnInit{

  isAdmin: boolean = false;
  connected: boolean = false;
 
  user: {username:string} = {username:''};
   
  constructor(private  apiService:ApiService, private router: Router, private authService: AuthenticateService){
    this.isAdmin = this.authService.isAdmin();
  }
  
  
  ngOnInit(): void {
    //this.connected = this.authService.isConnected();
    this.authService.connected$.subscribe((status) =>{
      this.isAdmin= this.authService.isAdmin();
      this.connected = status; 
      if(this.connected) {
        this.user = this.authService.getUser();
      }else{
        this.user = {username:''};  //clear user if disconnected
      }
     
    })
   
   
  }
  getUser(email: string, password:string):void{
    this.apiService.getUserByEmailAndPassword(email, password).subscribe((
      data:User) => {
        this.user = data;
      },
      (error) => {
          console.error('Error fetching user', error)
      }
      );

  }
  onUserConnected(connected: boolean){
    this.connected = connected;
  }
  
  disconnect(){
    this.authService.OnIsDeconnected();
    this.connected = false;
    this.router.navigateByUrl("hotels");
  }
}
