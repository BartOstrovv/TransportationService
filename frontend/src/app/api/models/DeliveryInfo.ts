import {Address} from "@api/models/Address";

export class DeliveryInfo {
  departureDate!: string;
  arrivalDate!: string;
  departureLocation!: Address;
  arrivalLocation!: Address;
}
