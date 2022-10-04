package original.transportationservicesapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "admins")
@DiscriminatorValue(value="ADMIN")
public class Admin extends User{

}
