import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "@api/models/User";
import {API_URL} from "@config/Constants";
import {Delivery} from "@api/models/Delivery";
import {RestPage} from "@api/models/RestPage";

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
}
