package original.transportationservicesapp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;

    @ManyToOne
    @JoinColumn
    private Transporter transporter;
    @ManyToOne
    @JoinColumn(name = "deliveryId")
    private Delivery delivery;
    @Column(columnDefinition = "boolean default false")
    private Boolean approved;

    public static Offer of(Double price, Transporter transporter, Delivery delivery) {
        Offer offer = new Offer();
        offer.setPrice(price);
        offer.setTransporter(transporter);
        offer.setDelivery(delivery);
        offer.setApproved(false);
        return offer;
    }
}
