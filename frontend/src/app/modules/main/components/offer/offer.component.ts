import {Component, NgModule, OnInit} from '@angular/core';
import {CommonModule} from "@angular/common";
import {ActivatedRoute, Router, RouterModule} from "@angular/router";
import {FormBuilder, FormsModule} from "@angular/forms";
import {DeliveryHttpService} from "@api/services/delivery-http.service";
import {first} from "rxjs";

@Component({
  selector: 'app-offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.scss']
})
export class OfferComponent implements OnInit {

  deliveryId: number;
  price: number = 0;
  constructor(private deliveryHttpService: DeliveryHttpService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
    this.deliveryId = activatedRoute.snapshot.params['id']
  }

  ngOnInit(): void {
  }

  add() {
    console.log("price", this.price)
    this.deliveryHttpService.offer(this.deliveryId, this.price)
      .pipe(first())
      .subscribe(
        {
          next: offer => {
            console.log(offer)
            this.router.navigate(['/deliveries/'])
          },
          error: error => console.error(error)
        })
  }
}

@NgModule({
  declarations: [
    OfferComponent
  ],
  imports: [
    RouterModule.forChild([{path: "", component: OfferComponent}]),
    CommonModule,
    FormsModule
  ]
})
export class OfferModule { }

