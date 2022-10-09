import {Component, NgModule, OnInit} from '@angular/core';
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}

@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    RouterModule.forChild([{path: "", component: DashboardComponent}]),
    CommonModule
  ]
})
export class DashboardModule { }
