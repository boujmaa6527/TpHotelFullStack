import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { AuthenticateService } from '../../services/authenticate.service';
import { Router } from '@angular/router';
import {of} from 'rxjs';
import { User } from '../../model/user.model';
import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let formBuilder : FormBuilder;
  let authService: jasmine.SpyObj<AuthenticateService>;
  let router: jasmine.SpyObj<Router>;
  let fixture: ComponentFixture<LoginComponent>;
  

  beforeEach(async () => {
   
    const authServiceSpy = jasmine.createSpyObj("AuthenticateService", ['getUser', 'isConnected']);
    const routerSpy = jasmine.createSpyObj('Router', ['navigate']);

    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports:[ReactiveFormsModule],
      providers: [
        FormBuilder,
        {provide: AuthenticateService, useValue: authServiceSpy},
        {provide: Router, useValue: routerSpy}
      ]
    }).compileComponents();
   

    formBuilder = TestBed.inject(FormBuilder);
    authService = TestBed.inject(AuthenticateService) as jasmine.SpyObj<AuthenticateService>;
    router = TestBed.inject(Router) as jasmine.SpyObj<Router>;
    
    //Configuration des valeurs de retour des mocks
    authService.getUser.and.returnValue(({
      username: 'rachid@gmail.com',
      password: '12345',
    } as User));

    authService.isConnected.and.returnValue(true);

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('devrait initialiser le formulaire correctement', () => {
    expect(component.myForm.value).toEqual({
      email: "rachid@gmail.com",
      password: "12345"
    });
  });

  it('verifie connexion', () => {
    expect(component.connected).toBe(true);
  });
});
