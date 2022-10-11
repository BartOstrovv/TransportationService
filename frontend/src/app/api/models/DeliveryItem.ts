import {MeasurementUnit} from "@api/models/enums/MeasurementUnit";

export class DeliveryItem {
  position!: string;
  number!: number;
  unit!: MeasurementUnit;
}
