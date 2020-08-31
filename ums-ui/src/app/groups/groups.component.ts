import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { GroupsService } from './groups.service';
import { UserService } from '../home/user.service';
import { User } from '../home/user';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css'],
})
export class GroupsComponent implements OnInit {
  groups: any[];
  newGroupName: string;
  users: User[];

  constructor(private authService: AuthService, private groupService: GroupsService, private userService: UserService) { }

  ngOnInit(): void {
    this.getAllGroups();
    this.getAllUsers();
  }

  logout() {
    this.authService.logout();
  }

  addGroup() {
    this.groupService.addGroup({ 'name': this.newGroupName }).subscribe(_ => this.getAllGroups());
  }

  getAllGroups() {
    this.groupService.getAllGroups().subscribe(grList => this.groups = grList);
  }

  removeGroup(id) {
    this.groupService.deleteGroup(id).subscribe(_ => this.getAllGroups());
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe(usrList => {this.users = usrList; console.log(usrList)});
  }

  changeUserGroup(user: User) {
    console.log(user);
    this.userService.changeUserGroup(user).subscribe();
  }
}
