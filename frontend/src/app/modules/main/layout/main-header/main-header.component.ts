import { Component, OnInit } from '@angular/core';
import {MenuItem} from "primeng/api";
import {first} from "rxjs";
import {AuthHttpService} from "@api/services/auth-http.service";

@Component({
  selector: 'app-main-header',
  templateUrl: './main-header.component.html',
  styleUrls: ['./main-header.component.scss']
})
export class MainHeaderComponent implements OnInit {

  userMenuItems: MenuItem[] = [];

  constructor(private authHttpService: AuthHttpService,
              /*public securityService: SecurityService*/) {
    this.initUserMenuItems();

    /*this.securityService.isAuthenticated$
      .pipe(untilDestroyed(this))
      .subscribe(isAuthenticated => {
        this.initUserMenuItems(isAuthenticated);
      })*/
  }

  ngOnInit(): void {
  }

  logout() {
    this.authHttpService.logout()
      .pipe(first())
      .subscribe({
        next: () => console.log("Logout"),//this.securityService.logout(),
        error: error => console.log(error)
      })
  }

  initUserMenuItems(/*isAuthenticated: boolean = this.securityService.isAuthenticated()*/) {
    this.userMenuItems = [
      {
        label: 'Login',
        icon: 'fa-solid fa-right-to-bracket',
        routerLink: '/login',
        visible: true//!isAuthenticated
      },
      {
        label: 'Admin panel',
        icon: 'fa-solid fa-screwdriver-wrench',
        routerLink: '/admin',
       // visible: this.securityService.hasRole(Role.ADMIN)
      },
      {
        label: 'Logout',
        icon: 'fa-solid fa-right-from-bracket',
        command: () => this.logout(),
        visible: true//isAuthenticated
      }
    ];
  }

}
