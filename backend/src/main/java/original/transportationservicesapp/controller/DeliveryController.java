package original.transportationservicesapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public Page<DeliveryDto> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/my")
    public Page<DeliveryDto> getAllByCurrentCustomer(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.getAllByCustomer(pageable);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/create")
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



