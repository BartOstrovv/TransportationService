package original.transportationservicesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import original.transportationservicesapp.enums.Role;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUserDto implements Serializable {
    private Long id;
    private String email;
    private Role role;
}
