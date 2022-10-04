import { Component } from '@angular/core';
import {User} from "./api/models/User";
import {UserHttpService} from "./api/services/user-http.service";
import {first} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';

  users: User[] = []

  constructor(private http:UserHttpService) {
    this.getUsers();
  }

  getUsers() {
    this.http.getAll()
      .pipe(first())
      .subscribe( {
        next: users => this.users = users,
        error: error=>console.error(error)
      })
  }
}
