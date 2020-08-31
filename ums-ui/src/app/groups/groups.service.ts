import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class GroupsService {

    ALL_GROUPS_URL = 'http://localhost:8080/api/groups';
    ADD_GROUP_URL = 'http://localhost:8080/api/addGroup';
    DELETE_GROUP_URL = 'http://localhost:8080/api/deleteGroup/';
    USERS_FOR_GROUP_URL = ""

    constructor(private http: HttpClient) { }

    private buildHttpHeaderOptions() {
        let headers: HttpHeaders = new HttpHeaders({
            'Authorization': 'Bearer ' + sessionStorage.getItem('token')
        });

        return { headers: headers };
    }

    getAllGroups() {
        return this.http.get<any[]>(this.ALL_GROUPS_URL, this.buildHttpHeaderOptions());
    }

    addGroup(groupJson) {
        return this.http.post(this.ADD_GROUP_URL, groupJson, this.buildHttpHeaderOptions());
    }

    deleteGroup(id: number) {
        return this.http.get(this.DELETE_GROUP_URL + id, this.buildHttpHeaderOptions());
    }
}