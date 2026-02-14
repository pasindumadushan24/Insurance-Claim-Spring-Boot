package lk.ijse.backend.ServiceLayer;

import jakarta.transaction.Transactional;
import lk.ijse.backend.DTO.CustomerDTO;
import lk.ijse.backend.Entity.Customer;
import lk.ijse.backend.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {
   private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

   @Override
    public CustomerDTO  saveCustomer(CustomerDTO customerDTO) {
//       customerRepository.save(modelMapper.map(customerDTO, Customer.class));
       Customer customer = modelMapper.map(customerDTO, Customer.class);

       Customer savedCustomer = customerRepository.save(customer);

       return modelMapper.map(savedCustomer, CustomerDTO.class);
   }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        customerRepository.save(modelMapper.map(customerDTO, Customer.class));
    }



    @Override
    public void deleteCustomer(Long cid) {
        customerRepository.deleteById(cid);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {}.getType());
    }


}