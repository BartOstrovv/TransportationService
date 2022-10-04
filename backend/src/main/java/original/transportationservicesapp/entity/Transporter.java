package original.transportationservicesapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "transporters")
@DiscriminatorValue(value="TRANSPORTER")
public class Transporter extends User {
    private String logo;
    private String description;
    private ZonedDateTime founded;

    @OneToMany(mappedBy = "transporter", fetch = FetchType.LAZY)
    private List<Offer> offers;
}
