package original.transportationservicesapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import original.transportationservicesapp.repository.UserRepo;

@Component
@RequiredArgsConstructor
public class DatabaseUserService implements UserDetailsService {
    private final UserRepo userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}