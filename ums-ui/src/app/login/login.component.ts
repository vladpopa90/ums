import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  model: any = {};

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  login() {
    this.authService.login(this.model.username, this.model.password).subscribe(token => { sessionStorage.setItem('token', token); this.router.navigate(['/home']) });
  }

}