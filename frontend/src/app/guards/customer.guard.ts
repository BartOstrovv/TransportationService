import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { SecurityService } from "@services/security.service";
import { Role } from "@api/models/enums/Role";

@Injectable({providedIn: "root"})
export class CustomerGuard implements CanActivate {

  constructor(private securityService: SecurityService,
              private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    console.log("Customer guard works")
    return this.securityService.hasRole(Role.CUSTOMER) ? true : this.router.createUrlTree(['/'])
  }

}
