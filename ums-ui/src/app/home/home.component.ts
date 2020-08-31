import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { UserService } from './user.service';
import { User } from './user';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    currentUser: User;
    users: User[];
    model = new User("", "", "", "", 0);

    constructor(private authService: AuthService, private homeService: UserService) { }

    ngOnInit() {
        this.homeService.getCurrentUser().subscribe(usr => { this.currentUser = usr; this.getAllUsers(); });
    }

    logout() {
        this.authService.logout();
    }

    onNewUserSubmit() {
        this.homeService.addUser(this.model).subscribe(_ => this.getAllUsers());

    }

    getAllUsers() {
        this.homeService.getAllUsers().subscribe(usrList => this.users = usrList);
    }

    removeUser(id) {
        this.homeService.deleteUser(id).subscribe(_ => this.getAllUsers());
    }
}
