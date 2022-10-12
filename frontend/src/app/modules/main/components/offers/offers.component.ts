import {Component, NgModule, OnInit} from '@angular/core';
import {CommonModule} from "@angular/common";
import {ActivatedRoute, Router, RouterModule} from "@angular/router";
import {OfferComponent} from "@modules/main/components/offer/offer.component";
import {ListboxModule} from "primeng/listbox";
import {Offer} from "@api/models/Offer";
import {FormsModule} from "@angular/forms";
import {ButtonModule} from "primeng/button";
import {first} from "rxjs";
import {DeliveryHttpService} from "@api/services/delivery-http.service";

@Component({
  selector: 'app-offers',
  templateUrl: './offers.component.html',
  styleUrls: ['./offers.component.scss']
})
export class OffersComponent implements OnInit {
  deliveryId!: number;
  offers!: Offer[];
  selectedOffer!: Offer;

  constructor(private activatedRoute: ActivatedRoute,
              private deliveryHttpService: DeliveryHttpService,
              private router: Router,) {
    this.deliveryId  = activatedRoute.snapshot.params['id']

    if (activatedRoute.snapshot.url.findIndex(()=>'deliveries'))
      console.log("From deliveries")
    else if (activatedRoute.snapshot.url.findIndex(()=>'transporters'))
      console.log("From transporters")
    if (this.deliveryId)
      this.getOffersFromDelivery();
  }

  ngOnInit(): void {
  }

  confirmSelected() {
    this.deliveryHttpService.confirm(this.deliveryId, this.selectedOffer.id)
      .pipe(first())
      .subscribe({
        next: delivery => console.log(delivery),
        error: error => console.error(error)
      })
  }

  private getOffersFromDelivery() {
    this.deliveryHttpService.get(this.deliveryId)
      .pipe(first())
      .subscribe({
        next: delivery => {
          this.offers = delivery.offers;
          console.log(this.offers);
        },
        error: error => console.error(error)
      })
  }
}

@NgModule({
  declarations: [
    OffersComponent
  ],
  imports: [
    RouterModule.forChild([{path: "", component: OffersComponent},
      {path:"transporters/:id/offers", component:OffersComponent}]),
    CommonModule,
    ListboxModule,
    FormsModule,
    ButtonModule
  ]
})
export class OffersModule { }
