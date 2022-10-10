package original.transportationservicesapp.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import original.transportationservicesapp.entity.User;
import original.transportationservicesapp.security.CustomUserDetails;

@Service
public class SecurityService {

    public String getCurrentUserEmail() {
        return getAuthentication().getName();
    }

    public CustomUserDetails getCurrentUserDetails() {
        return (CustomUserDetails) getAuthentication().getDetails();
    }

    public User getCurrentUser() {
        return getCurrentUserDetails().getUser();
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
