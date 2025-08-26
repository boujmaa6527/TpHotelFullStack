import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../../model/user.model';
import { AuthenticateService } from '../../services/authenticate.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit{
  [x: string]: any;
  
  myForm!: FormGroup;
  user!: User;
  error! : string;
  connected: boolean = false; 
  title!: "front-end";
  
  
  @Output() userConnected: EventEmitter<boolean> = new EventEmitter<boolean>; 

  
  constructor(private formBuilder:FormBuilder, public authService: AuthenticateService, private router: Router){
    this.user = this.authService.getUser();
    this.connected = this.authService.isConnected();
    this.myForm = this.formBuilder.group({
      email: [this.user.username,[Validators.required]],
      password: [this.user.password, [Validators.required]]
    })

  }
  
  ngOnInit(): void {
    this.user = new User(0, "", "", {id: 0, rolename:""});
  }

  onLogin(form: FormGroup){
    if(form.valid){
      try{
        this.authService.onLogin(form.value.email, form.value.password).subscribe({
          next: (data) =>{
            if(data){
              console.log("Data User", data);
              this.user = data;
              this.authService.setUser(this.user);
              this.authService.setConnected(true);
              this.connected = true;
             
              this.router.navigate(['hotels']); 
            }else{
              this.error = "Email ou pwd incorrecte";
            }
          },
          error: () => this.error = "Erreur API",
          complete: () => console.log("Connexion successFull")

        });

      }catch(e){
        this.error ="Erreur imprévue, Réessayer";
      }
    }else{
      this.error= "Erreur de saisie de formulaire";
    }
  }
  disconnect(){
    this.authService.disconnected();
    this.connected = false;
    
    this.router.navigateByUrl("hotels");
  }


}
