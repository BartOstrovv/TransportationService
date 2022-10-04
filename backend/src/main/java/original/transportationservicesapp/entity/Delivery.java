package original.transportationservicesapp.entity;

import lombok.*;
import original.transportationservicesapp.enums.DeliveryStatus;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private ZonedDateTime createdDate;

    @Embedded
    private DeliveryInfo info;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    private List<DeliveryItem> deliveryItems;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Offer> offers;
}
