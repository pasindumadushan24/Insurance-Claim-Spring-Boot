package lk.ijse.back_end.service.custom;

import lk.ijse.back_end.dto.CustomerDto;
import lk.ijse.back_end.entity.Customer;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDto customerDto);
    List<CustomerDto> getAllCustomers();
    void deleteCustomer(Long id);
}
