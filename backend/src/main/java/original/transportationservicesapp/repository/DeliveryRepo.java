package original.transportationservicesapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import original.transportationservicesapp.entity.Delivery;

@Repository
public interface DeliveryRepo extends JpaRepository<Delivery, Long> {
    Page<Delivery> findByCustomerId(Long id, Pageable pageable);
}
