package original.transportationservicesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDtoShort {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
