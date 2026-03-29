package lk.ijse.back_end.service;

import lk.ijse.back_end.dto.CustomerDto;
import lk.ijse.back_end.entity.Customer;
import lk.ijse.back_end.repository.CustomerRepository;
import lk.ijse.back_end.service.custom.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        Customer customer = new Customer(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getAddress(),
                customerDto.getContactNo(),
                customerDto.getInsuranceType(),
                customerDto.getPolicyNumber()
        );

        customerRepository.save(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerDto(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getContactNo(),
                        customer.getInsuranceType(),
                        customer.getPolicyNumber()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}