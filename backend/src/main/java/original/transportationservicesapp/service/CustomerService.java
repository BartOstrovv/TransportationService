package original.transportationservicesapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import original.transportationservicesapp.dto.CustomerDto;
import original.transportationservicesapp.entity.Customer;
import original.transportationservicesapp.exception.EntityNotFoundException;
import original.transportationservicesapp.mapper.Mapper;
import original.transportationservicesapp.repository.CustomerRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final Mapper mapper;

    public CustomerDto get(Long id) {
        return mapper.toCustomerDto(retrieve(id));
    }

    public List<CustomerDto> getAll() {
        return customerRepo.findAll().stream().map(mapper::toCustomerDto).collect(Collectors.toList());
    }

    public CustomerDto create(CustomerDto dto) {
        return mapper.toCustomerDto(customerRepo.save(mapper.toCustomer(dto)));
    }

    public CustomerDto update(Long id, CustomerDto dto) {
        Customer customer = retrieve(id);
        mapper.mergeCustomer(dto, customer);
        return mapper.toCustomerDto(customerRepo.save(customer));
    }

    public void delete(Long id) {
        customerRepo.deleteById(id);
    }

    private Customer retrieve(Long id) {
        return customerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer", id));
    }
}
