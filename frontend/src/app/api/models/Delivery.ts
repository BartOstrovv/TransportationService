import {DeliveryStatus} from "@api/models/enums/DeliveryStatus";
import {DeliveryInfo} from "@api/models/DeliveryInfo";
import {DeliveryItem} from "@api/models/DeliveryItem";

export class Delivery {
  id!: number;
  title!: string;
  description!: string;
  createdDate!: string;
  status!: DeliveryStatus;
  info!: DeliveryInfo;
  deliveryItems!: DeliveryItem[];
}
