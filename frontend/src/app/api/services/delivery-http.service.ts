import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "@api/models/User";
import {API_URL} from "@config/Constants";
import {Delivery} from "@api/models/Delivery";
import {RestPage} from "@api/models/RestPage";
import {DeliveriesComponent} from "@modules/main/components/deliveries/deliveries.component";
import {bootstrapApplication} from "@angular/platform-browser";

@Injectable({providedIn:'root'})
export class DeliveryHttpService {
  private readonly URL = `${API_URL}/deliveries`;

  constructor(private http:HttpClient) {
  }

  getAll() {
    return this.http.get<RestPage<Delivery>>(this.URL);
  }

  getMyDeliveries() {
    console.log("byCustomer")
    return this.http.get<RestPage<Delivery>>(`${this.URL}/my`);
  }

  delete(id: number) {
    return this.http.delete<void>(`${this.URL}/${id}`)
  }

  get(id: number) {
    return this.http.get<Delivery>(`${this.URL}/${id}`);
  }

  public update(delivery: Delivery) {
    return this.http.put<void>(`${this.URL}/${delivery.id}`, delivery)
  }

  public create(delivery: Delivery) {
    return this.http.post<void>(`${this.URL}/create`, delivery)
  }

  public offer(deliveryId: number, price: number) {
    return this.http.patch<Delivery>(`${this.URL}/${deliveryId}/offer/${price}`, {})
  }

  public confirm(deliveryId: number, offerId: number) {
    return this.http.patch<Delivery>(`${this.URL}/${deliveryId}/confirm/${offerId}`, {})
  }
}
