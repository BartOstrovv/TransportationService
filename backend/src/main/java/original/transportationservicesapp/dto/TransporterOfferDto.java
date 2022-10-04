package original.transportationservicesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransporterOfferDto {
    private Long id;
    private Double price;
    DeliveryDtoShort delivery;
}
