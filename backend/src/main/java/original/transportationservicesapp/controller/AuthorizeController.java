package original.transportationservicesapp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import original.transportationservicesapp.dto.ProfileDto;
import original.transportationservicesapp.dto.RegistrationCustomerDto;
import original.transportationservicesapp.dto.RegistrationTransporterDto;
import original.transportationservicesapp.service.AuthorizeService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {
    private final AuthorizeService service;
    @GetMapping("/transporter/signup")
    public ProfileDto signupAsTransporter(@RequestBody RegistrationTransporterDto dto) {
        return service.signupAsTransporter(dto);
    }

    @GetMapping("/customer/signup")
    public ProfileDto signupAsCustomer(@RequestBody RegistrationCustomerDto dto) {
        return service.signupAsCustomer(dto);
    }
}
