import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: any = {};

  constructor(
    private router: Router,
    private http: HttpClient
  ) { }

  ngOnInit() {
    sessionStorage.setItem('token', '');
  }

  login() {
    let url = 'http://localhost:8080/login';

    this.http.post(url, {
      'email': this.model.username,
      'password': this.model.password
    }, { responseType: 'text' }).subscribe(token => {
      console.log(token);
      sessionStorage.setItem(
        'token', token
      );

      this.router.navigate(['/home']);
    });
  }


}