package original.transportationservicesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import original.transportationservicesapp.enums.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationCustomerDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String phone;
}
