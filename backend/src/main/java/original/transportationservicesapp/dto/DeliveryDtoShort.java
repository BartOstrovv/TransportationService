package original.transportationservicesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import original.transportationservicesapp.entity.DeliveryInfo;
import original.transportationservicesapp.enums.DeliveryStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDtoShort {
    private Long id;
    private String title;
    private String description;
    private ZonedDateTime createdDate;
    private DeliveryStatus status;
    private DeliveryInfo info;
    private List<DeliveryItemDto> deliveryItems;
}
