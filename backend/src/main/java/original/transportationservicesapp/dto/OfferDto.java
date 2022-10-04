package original.transportationservicesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {
    private Long id;
    private Double price;
    private TransporterDtoShort transporter;
    private DeliveryDtoShort delivery;
    private Boolean approved;
}
