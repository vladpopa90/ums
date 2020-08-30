// import { Injectable } from '@angular/core';
// import { HttpClient, HttpHeaders } from '@angular/common/http';

// @Injectable()
// export class AuthService {

//   authenticated = false;
//   token = "";

//   login() {
//     let url = 'http://localhost:8080/login';

//     this.http.post(url, {
//       'email': this.model.username,
//       'password': this.model.password
//     }, { responseType: 'text' }).subscribe(token => {
//       console.log(token);
//       sessionStorage.setItem(
//         'token', token
//       );
//       this.token = token;

      
//     });
//   }

//   logout(): void {
//     this.authenticated = false;
//   }

// }