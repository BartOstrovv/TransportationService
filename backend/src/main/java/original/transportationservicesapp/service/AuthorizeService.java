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

    public ProfileDto signupAsTransporter(RegistrationTransporterDto dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));
        var tr = repoTransporter.save(mapper.toTransporter(dto));
        var profile = mapper.toProfileDto(tr);
        profile.setFullName(tr.getLogo() + " -> Owner(" + tr.getFirstName() + " " + tr.getLastName() + ")");
        return profile;
    }

    public ProfileDto signupAsCustomer(RegistrationCustomerDto dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));
        var customer = repoCustomer.save(mapper.toCustomer(dto));
        var profile = mapper.toProfileDto(customer);
        profile.setFullName(customer.getFirstName() + " " + customer.getLastName());
        return profile;
    }
}
