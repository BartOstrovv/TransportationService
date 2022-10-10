package original.transportationservicesapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DiscriminatorOptions;
import original.transportationservicesapp.enums.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "role")
@DiscriminatorOptions(force=true)
@Table(name = "users")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private ZonedDateTime created;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar default Customer", updatable = false, insertable = false)
    private Role role;

    @Column(columnDefinition = "boolean default true")
    private boolean enable;
}
