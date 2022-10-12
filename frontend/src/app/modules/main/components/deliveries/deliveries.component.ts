import {Component, NgModule, OnInit} from '@angular/core';
import {CommonModule} from "@angular/common";
import {ActivatedRoute, RouterModule} from "@angular/router";
import {DeliveryHttpService} from "@api/services/delivery-http.service";
import {SecurityService} from "../../../../services/security.service";
import {Delivery} from "@api/models/Delivery";
import {finalize, first} from "rxjs";
import {CardModule} from "primeng/card";
import {ButtonModule} from "primeng/button";
import {SelectItem} from "primeng/api";
import {DataViewModule} from "primeng/dataview";
import {PaginatorModule} from "primeng/paginator";
import {RestPage} from "@api/models/RestPage";
import {RatingModule} from "primeng/rating";
import {Role} from "@api/models/enums/Role";

@Component({
  selector: 'app-deliveries',
  templateUrl: './deliveries.component.html',
  styleUrls: ['./deliveries.component.scss']
})
export class DeliveriesComponent implements OnInit {

  //readonly DeliveryStatus = DeliveryStatus;
  deliveries: RestPage<Delivery> = new RestPage<Delivery>();
  role: Role = this.securityService.getUser().role;
  sortOptions: SelectItem[] = [
    {label: 'New first', value: '!createdDate'},
    {label: 'Old first', value: 'createdDate'}
  ];

  sortKey: string = this.sortOptions[0].value
  sortField: string = 'id';
  sortOrder: number = 1;
  constructor(private deliveryHttpService: DeliveryHttpService,
              private securityService: SecurityService,
              private activatedRoute: ActivatedRoute) { console.log("Sort Key is ", this.sortKey)}

  ngOnInit(): void {
    this.getDeliveries();
  }

  onSortChange(event: any) {
    let value = event.value;
    console.log(value)
  }

  getDeliveries() {
    if (this.activatedRoute.snapshot.params['my']) {
      this.deliveryHttpService.getMyDeliveries().pipe(first()).subscribe({
        next: (deliveries) => {
          this.deliveries = deliveries;
          console.log(this.deliveries);
        },
        error: (error) => {
          console.log(error);
        }
      });
    }
    else {
      this.deliveryHttpService.getAll().pipe(first()).subscribe({
        next: (deliveries) => {
          this.deliveries = deliveries;
          console.log(this.deliveries);
        },
        error: (error) => {
          console.log(error);
        }
      });
    }
  }


 //REMOVE
  removeDelivery($delivery: Delivery) {
    this.deliveryHttpService.delete($delivery.id).pipe()
      .subscribe({
        next: () => {
          //this.deliveries = this.deliveries.filter(x => x.id !== $vehicle.id);
        },
        error: (error) => console.error(error)
      }
    )
  }
}


@NgModule({
  declarations: [
    DeliveriesComponent
  ],
  imports: [
    RouterModule.forChild([{path: "", component: DeliveriesComponent},
      {path: "deliveries/my", component: DeliveriesComponent,}]),
    CommonModule,
    CardModule,
    ButtonModule,
    DataViewModule,
    PaginatorModule,
    RatingModule
  ]
})
export class DeliveriesModule { }

