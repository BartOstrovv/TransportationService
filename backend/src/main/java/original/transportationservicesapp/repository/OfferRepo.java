package original.transportationservicesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import original.transportationservicesapp.entity.Offer;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {
}
