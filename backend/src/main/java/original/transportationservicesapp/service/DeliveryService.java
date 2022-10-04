package original.transportationservicesapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import original.transportationservicesapp.dto.DeliveryDto;
import original.transportationservicesapp.entity.Customer;
import original.transportationservicesapp.entity.Delivery;
import original.transportationservicesapp.entity.Offer;
import original.transportationservicesapp.entity.Transporter;
import original.transportationservicesapp.enums.DeliveryStatus;
import original.transportationservicesapp.exception.DeliveryAlreadyHaveFinalOfferException;
import original.transportationservicesapp.exception.DeliveryNotContainsOfferException;
import original.transportationservicesapp.exception.EntityNotFoundException;
import original.transportationservicesapp.mapper.Mapper;
import original.transportationservicesapp.repository.CustomerRepo;
import original.transportationservicesapp.repository.DeliveryRepo;
import original.transportationservicesapp.repository.OfferRepo;
import original.transportationservicesapp.repository.TransporterRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepo deliveryRepo;
    private final OfferRepo offerRepo;
    private final TransporterRepo transporterRepo;
    private final CustomerRepo customerRepo;
    private final Mapper mapper;

    public DeliveryDto get(Long id) {
        return mapper.toDeliveryDto(retrieve(id));
    }

    public List<DeliveryDto> getAll() {
        return deliveryRepo.findAll().stream().map(mapper::toDeliveryDto).collect(Collectors.toList());
    }

    public DeliveryDto create(DeliveryDto dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer cm = customerRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        dto.setCustomer(mapper.toCustomerDtoShort(cm));
        return mapper.toDeliveryDto(deliveryRepo.save(mapper.toDelivery(dto)));
    }

    public DeliveryDto offer(Long id, Double price) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Transporter tr = transporterRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        Delivery delivery = retrieve(id);
        offerRepo.save(Offer.of(price, tr, delivery));
        if (DeliveryStatus.afterAccepted().contains(delivery.getStatus()))
            throw new DeliveryAlreadyHaveFinalOfferException("Delivery", delivery.getId());
        if (delivery.getStatus() == DeliveryStatus.CREATED)
            delivery.setStatus(DeliveryStatus.UNCONFIRMED_OFFERS);
        return mapper.toDeliveryDto(deliveryRepo.save(delivery));
    }

    public DeliveryDto confirmOffer(Long id, Long offerID) {
        Delivery delivery = retrieve(id);
        delivery.setStatus(DeliveryStatus.CONFIRMED_OFFER);
        Offer offer = offerRepo.findById(offerID).orElseThrow(() -> new EntityNotFoundException("Offer", offerID));
        if (offer.getDelivery().getId() != id)
            throw new DeliveryNotContainsOfferException("Delivery", id, offerID);
        offer.setApproved(true);
        offerRepo.save(offer);
        return mapper.toDeliveryDto(deliveryRepo.save(delivery));
    }

    public DeliveryDto update(Long id, DeliveryDto dto) {
        Delivery delivery = retrieve(id);
        mapper.mergeDelivery(dto, delivery);
        return mapper.toDeliveryDto(deliveryRepo.save(delivery));
    }

    public void delete(Long id) {
        deliveryRepo.deleteById(id);
    }

    private Delivery retrieve(Long id) {
        return deliveryRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Delivery", id));
    }

    public DeliveryDto arrive(Long id) {
        Delivery delivery = retrieve(id);
        Offer first = delivery.getOffers().stream().filter(x -> x.getApproved()).findFirst()
                .orElseThrow(() -> new DeliveryNotContainsOfferException("Delivery", id));
        if (delivery.getStatus() != DeliveryStatus.CONFIRMED_OFFER)
            throw new DeliveryNotContainsOfferException("Delivery", id);
        delivery.setStatus(DeliveryStatus.ARRIVED);
        first.setApproved(true);
        offerRepo.save(first);
        return mapper.toDeliveryDto(deliveryRepo.save(delivery));
    }

    public DeliveryDto done(Long id) {
        Delivery delivery = retrieve(id);
        if (delivery.getStatus() != DeliveryStatus.ARRIVED)
            throw new DeliveryNotContainsOfferException("Delivery", id);
        delivery.setStatus(DeliveryStatus.DONE);
        return mapper.toDeliveryDto(deliveryRepo.save(delivery));
    }

    public List<DeliveryDto> getAllByCustomer() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer cm = customerRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return cm.getDeliveries().stream().map(mapper::toDeliveryDto).collect(Collectors.toList());
    }
}
