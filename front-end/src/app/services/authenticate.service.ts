import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { User } from '../model/user.model';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  private connectedSubject = new BehaviorSubject<boolean>(false);
  connected$ = this.connectedSubject.asObservable();

  userConnected: User = new User(0, "", "", {id: 0, rolename: ""})

  constructor(private apiService: ApiService) { }

  setConnected(status: boolean){
    this.connectedSubject.next(status);
  }
  OnIsConnected(): boolean{
    return this.connectedSubject.getValue();
  }
  OnIsDeconnected(){
    this.connectedSubject.next(false);
  }
  getUser(){

   /* let user = localStorage.getItem('user');
    if(user){
      console.log("User base64: ", user);
      console.log("Decoded user: ", atob(user));
      try{
        this.userConnected = JSON.parse(atob(user));
      }catch(e){
        console.error("Erreur lors du decryptage ou parsing:", e);
        this.userConnected = new User(0, "", "", {id: 0, rolename: ""});
      }
    }else{
      this.userConnected = new User(0, "", "", {id: 0, rolename: ""});
    }
   
    return this.userConnected;*/
    const userEncoded = localStorage.getItem('user'); 
    if(!userEncoded) return new User(0, "","", {id: 0, rolename: ''});

    try{
      const decoded = atob(userEncoded);
      const parsed = JSON.parse(decoded);

      if(parsed && parsed.role && parsed.role.rolename){
        return parsed;
      }
    } catch(e){
      console.error('Erreur parsing user: ', e);
    }
    return new User(0, '', '', {id: 0, rolename: ''});
  }


  onLogin(email: string, password: string){
    return this.apiService.getUserByEmailAndPassword(email, password);
  }

  isConnected(){
    return localStorage.getItem("user") != null;
  }
  disconnected(){
    localStorage.removeItem("user"); 
    this.userConnected = new User(0, "", "", {id: 0 , rolename: ""});
  }

  isAdmin(): boolean{
    let user  = this.getUser();
   /* console.log("User:" , user);
    if(user?.role?.rolename){
      return user?.role?.rolename === 'ADMIN';
    }
    if(Array.isArray(user?.role)){
      return user.role.some((role: { rolename: string; }) => role.rolename === 'ADMIN');
    }

    return false; */
    return user && user.role && user.role.rolename ===  'ADMIN';
     
  }
  isUser(){
    let user  = this.getUser();
    return user && user.role && user.role.rolename === 'USER';
  }

  setUser(user:User): any{
    user.password= '';
    this.userConnected = user;
    localStorage.setItem('user', btoa(JSON.stringify(user)))
  }


}
