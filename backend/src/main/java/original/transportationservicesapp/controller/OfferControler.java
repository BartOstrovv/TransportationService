package original.transportationservicesapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import original.transportationservicesapp.dto.OfferDto;
import original.transportationservicesapp.service.OfferService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/offers")
public class OfferControler {
    private final OfferService service;
    @GetMapping("/{id}")
    public OfferDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<OfferDto> getAll() {
        return service.getAll();
    }

    @PostMapping
    public OfferDto create(@RequestBody OfferDto offer) {
        return service.create(offer);
    }

    @PutMapping("/{id}")
    public OfferDto update(@PathVariable Long id, @RequestBody OfferDto offer) {
        return service.update(id, offer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
