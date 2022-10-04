package original.transportationservicesapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "customers")
@DiscriminatorValue(value="CUSTOMER")
public class Customer extends User {
    @Column(nullable = false, unique = true)
    private String phone;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Delivery> deliveries;
}
