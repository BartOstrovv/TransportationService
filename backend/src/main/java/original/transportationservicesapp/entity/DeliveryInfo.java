package original.transportationservicesapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import original.transportationservicesapp.dto.Address;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.ZonedDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryInfo {
    private ZonedDateTime departureDate;
    private ZonedDateTime arrivalDate;
    @AttributeOverrides({
            @AttributeOverride( name = "country", column = @Column(name = "departure_country")),
            @AttributeOverride( name = "city", column = @Column(name = "departure_city")),
            @AttributeOverride( name = "street", column = @Column(name = "departure_street")),
            @AttributeOverride( name = "build", column = @Column(name = "departure_build"))
    })
    private Address departureLocation;
    @AttributeOverrides({
            @AttributeOverride( name = "country", column = @Column(name = "arrival_country")),
            @AttributeOverride( name = "city", column = @Column(name = "arrival_city")),
            @AttributeOverride( name = "street", column = @Column(name = "arrival_street")),
            @AttributeOverride( name = "build", column = @Column(name = "arrival_build"))
    })
    private Address arrivalLocation;
}
