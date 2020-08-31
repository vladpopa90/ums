import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthService {

    LOGIN_URL = 'http://localhost:8080/login';

    constructor(private http: HttpClient) { }

    login(username, password) {
        return this.http.post(this.LOGIN_URL, {
            'email': username,
            'password': password
        }, { responseType: 'text' });
    }

    logout(): void {
        sessionStorage.setItem('token', '');
    }

    isAuthenticated(): boolean {
        return (sessionStorage.getItem('token')) ? true : false;
    }

}