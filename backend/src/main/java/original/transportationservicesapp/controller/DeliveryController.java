package original.transportationservicesapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import original.transportationservicesapp.dto.DeliveryDto;
import original.transportationservicesapp.service.DeliveryService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    private final DeliveryService service;
    @GetMapping("/{id}")
    public DeliveryDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<DeliveryDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/myDeliveries")
    public List<DeliveryDto> getAllByCurrentCustomer() {
        return service.getAllByCustomer();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public DeliveryDto create(@RequestBody DeliveryDto delivery) {
        return service.create(delivery);
    }

    @PutMapping("/{id}")
    public DeliveryDto update(@PathVariable Long id, @RequestBody DeliveryDto delivery) {
        return service.update(id, delivery);
    }

    @PreAuthorize("hasRole('TRANSPORTER')")
    @PatchMapping("{id}/offer/{price}")
    public DeliveryDto addOffer(@PathVariable Long id, @PathVariable Double price) {
        return service.offer(id, price);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PatchMapping("{id}/confirm/{offerID}")
    public DeliveryDto confirmOffer(@PathVariable Long id, @PathVariable Long offerID) {
        return service.confirmOffer(id, offerID);
    }

    @PreAuthorize("hasRole('TRANSPORTER')")
    @PatchMapping("{id}/arrive")
    public DeliveryDto arriveDelivery(@PathVariable Long id) {
        return service.arrive(id);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PatchMapping("{id}/done")
    public DeliveryDto doneDelivery(@PathVariable Long id) {
        return service.done(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}



