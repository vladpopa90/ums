import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({ providedIn: 'root' })
export class UserService {

    USER_DETAILS_URL = 'http://localhost:8080/api/user';
    ALL_USERS_URL = 'http://localhost:8080/api/users';
    ADD_USER_URL = 'http://localhost:8080/api/addUser';
    DELETE_USER_URL = 'http://localhost:8080/api/deleteUser/';
    CHANGE_USER_GROUP_URL = 'http://localhost:8080/api/changeUserGroup'

    constructor(private http: HttpClient) { }

    private buildHttpHeaderOptions() {
        let headers: HttpHeaders = new HttpHeaders({
            'Authorization': 'Bearer ' + sessionStorage.getItem('token')
        });

        return { headers: headers };
    }

    getCurrentUser(): Observable<User> {
        return this.http.get<User>(this.USER_DETAILS_URL, this.buildHttpHeaderOptions());
    }

    getAllUsers(): Observable<User[]> {
        return this.http.get<User[]>(this.ALL_USERS_URL, this.buildHttpHeaderOptions());
    }

    addUser(user: User) {
        return this.http.post(this.ADD_USER_URL, user, this.buildHttpHeaderOptions());
    }

    deleteUser(id: number) {
        return this.http.get(this.DELETE_USER_URL + id, this.buildHttpHeaderOptions());
    }

    changeUserGroup(user: User) {
        return this.http.post(this.CHANGE_USER_GROUP_URL, user, this.buildHttpHeaderOptions());
    }

}