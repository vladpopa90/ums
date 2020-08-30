import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css'],
})
export class GroupsComponent implements OnInit {
  groups;
  newGroupName: string;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getAllGroups();
  }

  logout() {
    sessionStorage.setItem('token', '');
  }

  addGroup() {
    let url = 'http://localhost:8080/api/addGroup';
    this.http.post(url, {
      'name': this.newGroupName
    }).subscribe(successfull => {
      console.log('added group successfully.');
      this.getAllGroups();
    })
  }

  getAllGroups() {
    let url = 'http://localhost:8080/api/groups';

    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + sessionStorage.getItem('token')
    });

    let options = { headers: headers };
    this.http.get<Observable<Object[]>>(url, options).
      subscribe(groups => {
        this.groups = groups;
      },
        error => {
          if (error.status == 401)
            alert('Unauthorized');
        }
      );
  }

  removeGroup(id) {
    let url = 'http://localhost:8080/api/deleteGroup/' + id;

    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + sessionStorage.getItem('token')
    });

    let options = { headers: headers };
    this.http.get<Observable<Object[]>>(url, options).
      subscribe(successfull => {
        console.log('removed group successfully.');
        this.getAllGroups();
      })
  }

}
