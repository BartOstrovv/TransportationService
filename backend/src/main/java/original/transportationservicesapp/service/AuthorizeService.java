package original.transportationservicesapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import original.transportationservicesapp.dto.ProfileDto;
import original.transportationservicesapp.dto.RegistrationCustomerDto;
import original.transportationservicesapp.dto.RegistrationTransporterDto;
import original.transportationservicesapp.mapper.Mapper;
import original.transportationservicesapp.repository.CustomerRepo;
import original.transportationservicesapp.repository.TransporterRepo;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorizeService {
    //private final UserRepo repo;
    private final TransporterRepo repoTransporter;
    private final CustomerRepo repoCustomer;
    private final Mapper mapper;
    private final PasswordEncoder encoder;

    public ProfileDto signupAsTransporter(RegistrationTransporterDto registrationDto) {
        registrationDto.setPassword(encoder.encode(registrationDto.getPassword()));
        var transporter = repoTransporter.save(mapper.toTransporter(registrationDto));
        var profile = mapper.toProfileDto(transporter);
        profile.setFullName(transporter.getLogo() + 
                " -> Owner(" + transporter.getFirstName() + " " + transporter.getLastName() + ")");
        return profile;
    }

    public ProfileDto signupAsCustomer(RegistrationCustomerDto registrationDto) {
        registrationDto.setPassword(encoder.encode(registrationDto.getPassword()));
        var customer = repoCustomer.save(mapper.toCustomer(registrationDto));
        var profile = mapper.toProfileDto(customer);
        profile.setFullName(customer.getFirstName() + " " + customer.getLastName());
        return profile;
    }
}
