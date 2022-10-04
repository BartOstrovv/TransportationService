package original.transportationservicesapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import original.transportationservicesapp.dto.CustomerDto;
import original.transportationservicesapp.service.CustomerService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;
    @GetMapping("/{id}")
    public CustomerDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<CustomerDto> getAll() {
        return service.getAll();
    }

    @PostMapping
    public CustomerDto create(@RequestBody CustomerDto customer) {
        return service.create(customer);
    }

    @PutMapping("/{id}")
    public CustomerDto update(@PathVariable Long id, @RequestBody CustomerDto customer) {
        return service.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
