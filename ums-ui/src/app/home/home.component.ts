import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { User } from './user';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    userName: string;
    currentUser;
    users;
    model = new User("", "", "", "");

    constructor(private http: HttpClient) { }

    ngOnInit() {
        let url = 'http://localhost:8080/api/user';

        let headers: HttpHeaders = new HttpHeaders({
            'Authorization': 'Bearer ' + sessionStorage.getItem('token')
        });

        let options = { headers: headers };
        this.http.get<Observable<Object>>(url, options).
            subscribe(user => {
                this.userName = user['firstName'];
                this.currentUser = user;
                this.getAllUsers();
            },
                error => {
                    if (error.status == 401)
                        alert('Unauthorized');
                }
            );
    }

    logout() {
        sessionStorage.setItem('token', '');
    }

    onNewUserSubmit() {
        let url = 'http://localhost:8080/api/addUser';

        let headers: HttpHeaders = new HttpHeaders({
            'Authorization': 'Bearer ' + sessionStorage.getItem('token')
        });

        let options = { headers: headers };
        this.http.post(url, this.model, options
        ).subscribe(successfull => {
            console.log('added user successfully.');
            this.getAllUsers();
        })
    }

    getAllUsers() {
        let url = 'http://localhost:8080/api/users';

        let headers: HttpHeaders = new HttpHeaders({
            'Authorization': 'Bearer ' + sessionStorage.getItem('token')
        });

        let options = { headers: headers };
        this.http.get<Observable<Object[]>>(url, options).
            subscribe(users => {
                this.users = users;
            },
                error => {
                    if (error.status == 401)
                        alert('Unauthorized');
                }
            );
    }

    removeUser(id) {
        let url = 'http://localhost:8080/api/deleteUser/' + id;

        let headers: HttpHeaders = new HttpHeaders({
            'Authorization': 'Bearer ' + sessionStorage.getItem('token')
        });

        let options = { headers: headers };
        this.http.get<Observable<Object[]>>(url, options).
            subscribe(successfull => {
                console.log('removed user successfully.');
                this.getAllUsers();
            })
    }


}
