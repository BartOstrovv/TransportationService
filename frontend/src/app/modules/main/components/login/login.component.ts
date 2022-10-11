import {Component, NgModule, OnInit} from '@angular/core';
import {CommonModule} from "@angular/common";
import {Router, RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {ButtonModule} from "primeng/button";
import {AuthHttpService} from "@api/services/auth-http.service";
import {first} from "rxjs";
import {InputTextModule} from "primeng/inputtext";
import {PasswordModule} from "primeng/password";
import {Role} from "@api/models/enums/Role";
import {SecurityService} from "../../../../services/security.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  credentials = {
    email: '',
    password: '',
  }

  constructor(private router: Router, private authService: AuthHttpService,
              private securityService: SecurityService) { }

  login() {
    console.log(this.credentials);
    this.authService.login(this.credentials)
      .pipe(first())
      .subscribe(
        {
          next: user =>{
            this.securityService.login(user);
            if (user.role == Role.CUSTOMER)
              this.router.navigate(["/deliveries/my"]);
            else if (user.role == Role.TRANSPORTER)
              this.router.navigate(["/deliveries"]);
            else if (user.role == Role.ADMIN)
              this.router.navigate(["/admin"]);
          },
          error: error => console.error(error)
        }
      )
  }



  ngOnInit(): void {
  }

}

@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    RouterModule.forChild([{path: "", component: LoginComponent}]),
    CommonModule,
    FormsModule,
    InputTextModule,
    ButtonModule,
    PasswordModule
  ]
})
export class LoginModule { }
