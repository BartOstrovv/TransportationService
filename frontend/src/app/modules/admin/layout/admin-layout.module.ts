import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AdminLayoutComponent} from "@modules/admin/layout/admin-layout.component";
import {RouterModule} from "@angular/router";
import { AdminHeaderComponent } from './admin-header/admin-header.component';
import {AvatarModule} from "primeng/avatar";
import {MenuModule} from "primeng/menu";



@NgModule({
  declarations: [
    AdminLayoutComponent,
    AdminHeaderComponent
  ],
  imports: [
    RouterModule,
    CommonModule,
    AvatarModule,
    MenuModule
  ]
})
export class AdminLayoutModule { }
