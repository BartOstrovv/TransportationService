import {Component, NgModule, OnInit} from '@angular/core';
import {CommonModule} from "@angular/common";
import {ActivatedRoute, Router, RouterModule} from "@angular/router";
import {DeliveriesComponent} from "@modules/main/components/deliveries/deliveries.component";
import {Delivery} from "@api/models/Delivery";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {DeliveryHttpService} from "@api/services/delivery-http.service";
import {first} from "rxjs";
import {Role} from "@api/models/enums/Role";
import {CalendarModule} from "primeng/calendar";
import {DeliveryItem} from "@api/models/DeliveryItem";

@Component({
  selector: 'app-delivery',
  templateUrl: './delivery.component.html',
  styleUrls: ['./delivery.component.scss']
})
export class DeliveryComponent implements OnInit {

  formGroup!: FormGroup
  delivery?: Delivery
  deliveryItems!: DeliveryItem[];

  constructor(private deliveryHttpService: DeliveryHttpService,
              private router: Router,
              private formBuilder: FormBuilder,
              private activatedRoute: ActivatedRoute) {
    this.initFormGroup();
    const deliveryId = activatedRoute.snapshot.params['id']
    if (deliveryId)
      this.getDelivery(deliveryId);
  }

  ngOnInit(): void {

  }

  initFormGroup() {
    this.formGroup = this.formBuilder.group({
      title: [null, Validators.required],
      description: [null, Validators.required],
      //createdDate: [null, Validators.required],
      info: this.formBuilder.group({
        departureDate: [null, Validators.required],
        arrivalDate: [null, Validators.required],
        departureLocation: this.formBuilder.group({
          country: [null, Validators.required],
          city: [null, Validators.required],
          street: [null, Validators.required],
          build: [null, Validators.required]
        }),
        arrivalLocation: this.formBuilder.group({
          country: [null, Validators.required],
          city: [null, Validators.required],
          street: [null, Validators.required],
          build: [null, Validators.required]
        }),
        //deliveryItems: [null, Validators.required]
      })
    })
  }

  private getDelivery(id: number) {
    this.deliveryHttpService.get(id)
      .pipe(first())
      .subscribe({
        next: delivery => {
          this.delivery = delivery;
          this.formGroup.patchValue(delivery);
        },
        error: error => console.error(error)
      })
  }

  save() {
    console.log("SAVE")
    let delivery: Delivery = this.formGroup.value;
    if (this.delivery) {
      delivery.deliveryItems = this.deliveryItems;
      this.updateDelivery({...delivery, id: this.delivery.id})
    } else {
      delivery.deliveryItems = this.deliveryItems;
      this.createDelivery(delivery);
    }
  }


  private createDelivery(delivery: Delivery) {
    console.log("CREATE")
    this.deliveryHttpService.create(delivery)
      .pipe(first())
      .subscribe({
        next: delivery => {
          console.log(delivery)
          this.router.navigate(['/deliveries/my'])
        },
        error: error => console.error(error)
      })
  }

  private updateDelivery(delivery: Delivery) {
    this.deliveryHttpService.update(delivery)
      .pipe(first())
      .subscribe(
        {
          next: delivery => {
            console.log(delivery)
            this.router.navigate(['/deliveries/my'])
          },
          error: error => console.error(error)
        })
  }
}

@NgModule({
  declarations: [
    DeliveryComponent
  ],
  imports: [
    RouterModule.forChild([{path: "", component: DeliveryComponent},
      {path: "deliveries/create", component: DeliveryComponent}]),
    CommonModule,
    FormsModule,
    CalendarModule,
    ReactiveFormsModule
  ]
})
export class DeliveryModule {
}
