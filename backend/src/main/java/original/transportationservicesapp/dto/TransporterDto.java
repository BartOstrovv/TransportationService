package original.transportationservicesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransporterDto {
    private Long id;
    private String Logo;
    private String description;
    private List<TransporterOfferDto> offers;
}
