package original.transportationservicesapp.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import original.transportationservicesapp.entity.Customer;
import original.transportationservicesapp.entity.User;
import original.transportationservicesapp.security.CustomUserDetails;

@Service
public class SecurityService {

    public String getCurrentUserEmail() {
        return getAuthentication().getName();
    }

    public CustomUserDetails getCurrentUserPrincipals() {
        return (CustomUserDetails) getAuthentication().getPrincipal();
    }

    public User getCurrentUser() {
        return getCurrentUserPrincipals().getUser();
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
