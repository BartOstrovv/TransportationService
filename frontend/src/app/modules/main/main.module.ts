import { NgModule } from '@angular/core';
import {routing} from "@modules/main/main.routing";
import {MainLayoutModule} from "@modules/main/layout/main-layout.module";
import {OfferComponent} from "@modules/main/components/offer/offer.component";



@NgModule({
  declarations: [
  ],
  imports: [
    routing,
    MainLayoutModule
  ]
})
export class MainModule { }
