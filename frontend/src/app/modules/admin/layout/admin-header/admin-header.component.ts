import { Component, OnInit } from '@angular/core';
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";
import {MenuItem} from "primeng/api";
import {AuthHttpService} from "@api/services/auth-http.service";
import {first} from "rxjs";
import {SecurityService} from "../../../../services/security.service";

@UntilDestroy({ checkProperties: true })
@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.scss']
})
export class AdminHeaderComponent implements OnInit {
  userMenuItems: MenuItem[] = [];

  constructor(private authHttpService: AuthHttpService,
              private securityService: SecurityService) {
    this.initUserMenuItems();

    this.securityService.isAuthenticated$
      .pipe(untilDestroyed(this))
      .subscribe(isAuthenticated => {
        this.initUserMenuItems(isAuthenticated);
      })
  }

  ngOnInit(): void {
  }

  logout() {
    this.authHttpService.logout()
      .pipe(first())
      .subscribe({
        next: () => {
          this.securityService.logout()
        },
        error: error => console.log(error)
      })
  }

  initUserMenuItems(isAuthenticated: boolean = this.securityService.isAuthenticated()) {
    this.userMenuItems = [
      {
        label: 'Login',
        icon: 'fa-solid fa-right-to-bracket',
        routerLink: '/login',
        visible: !isAuthenticated
      },
      {
        label: 'View web site',
        icon: 'fa-solid fa-laptop',
        routerLink: '/',
      },
      {
        label: 'Logout',
        icon: 'fa-solid fa-right-from-bracket',
        command: () => this.logout(),
        visible: isAuthenticated
      }
    ];
  }

}

