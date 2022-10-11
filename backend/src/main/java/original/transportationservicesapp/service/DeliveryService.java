package original.transportationservicesapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepo deliveryRepo;
    private final OfferRepo offerRepo;
    private final CustomerRepo customerRepo;
    private final Mapper mapper;

    private final SecurityService securityService;

    public DeliveryDto get(Long id) {
        return mapper.toDeliveryDto(retrieve(id));
    }

    public Page<DeliveryDto> getAll(Pageable pageable) {
        return deliveryRepo.findAll(pageable).map(mapper::toDeliveryDto);
    }

    public DeliveryDto create(DeliveryDto dto) {
        Customer customer = (Customer) securityService.getCurrentUser();
        dto.setCustomer(mapper.toCustomerDtoShort(customer));
        return mapper.toDeliveryDto(deliveryRepo.save(mapper.toDelivery(dto)));
    }

    public DeliveryDto offer(Long id, Double price) {
        Transporter transporter = (Transporter) securityService.getCurrentUser();
        Delivery delivery = retrieve(id);
        offerRepo.save(Offer.of(price, transporter, delivery));
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

    public Page<DeliveryDto> getAllByCustomer(Pageable pageable) {
        //Customer customer = (Customer) securityService.getCurrentUser();

        //Customer customer = customerRepo.findByEmail(securityService.getCurrentUserEmail()).orElseThrow(() -> new EntityNotFoundException());
        //List<DeliveryDto> list = customer.getDeliveries().stream().map(mapper::toDeliveryDto).collect(Collectors.toList());
        return deliveryRepo.findByCustomerId(securityService.getCurrentUser().getId(), pageable).map(mapper::toDeliveryDto);
    }
}
