package original.transportationservicesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import original.transportationservicesapp.entity.Transporter;

import java.util.Optional;

@Repository
public interface TransporterRepo extends JpaRepository<Transporter, Long> {
    Optional<Transporter> findByEmail(String email);
}
