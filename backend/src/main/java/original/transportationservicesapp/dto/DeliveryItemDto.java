package original.transportationservicesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import original.transportationservicesapp.enums.MeasurementUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryItemDto {
    private String position;
    private Double number;
    private MeasurementUnit unit;

}
