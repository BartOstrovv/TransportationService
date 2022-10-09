import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MainLayoutComponent} from "@modules/main/layout/main-layout.component";
import {RouterModule} from "@angular/router";
import { MainHeaderComponent } from './main-header/main-header.component';
import {AvatarModule} from "primeng/avatar";
import {MenuModule} from "primeng/menu";



@NgModule({
  declarations: [
    MainLayoutComponent,
    MainHeaderComponent
  ],
  imports: [
    RouterModule,
    CommonModule,
    AvatarModule,
    MenuModule
  ]
})
export class MainLayoutModule { }
