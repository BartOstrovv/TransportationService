import {Component, NgModule, OnInit} from '@angular/core';
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {ButtonModule} from "primeng/button";
import {AuthHttpService} from "@api/services/auth-http.service";
import {first} from "rxjs";
import {InputTextModule} from "primeng/inputtext";

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

  constructor(private authService: AuthHttpService) { }

  login() {
    console.log(this.credentials);
    this.authService.login(this.credentials)
      .pipe(first())
      .subscribe(
        {
          next: user => console.log("Logged in", user),
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
    ButtonModule
  ]
})
export class LoginModule { }
