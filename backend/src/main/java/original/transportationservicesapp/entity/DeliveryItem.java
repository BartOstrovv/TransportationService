package original.transportationservicesapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import original.transportationservicesapp.enums.MeasurementUnit;

import javax.persistence.*;

@Entity
@Table(name = "delivery_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String position;
    private Double number;

    @Enumerated(EnumType.STRING)
    private MeasurementUnit unit;

    @ManyToOne
    private Delivery delivery;
}
