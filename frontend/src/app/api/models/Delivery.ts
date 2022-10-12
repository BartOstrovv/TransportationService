import {DeliveryStatus} from "@api/models/enums/DeliveryStatus";
import {DeliveryInfo} from "@api/models/DeliveryInfo";
import {DeliveryItem} from "@api/models/DeliveryItem";
import {Offer} from "@api/models/Offer";

export class Delivery {
  id!: number;
  title!: string;
  description!: string;
  createdDate!: string;
  status!: DeliveryStatus;
  info!: DeliveryInfo;
  deliveryItems!: DeliveryItem[];
  offers!: Offer[];
}
