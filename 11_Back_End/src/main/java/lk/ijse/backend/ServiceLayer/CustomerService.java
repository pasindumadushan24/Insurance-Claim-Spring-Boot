package lk.ijse.backend.ServiceLayer;

import lk.ijse.backend.DTO.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public CustomerDTO  saveCustomer(CustomerDTO customerDTO);

    public void updateCustomer(CustomerDTO customerDTO);
//    public void deleteCustomer(Long cid);

    void deleteCustomer(Long cid);

    List<CustomerDTO> getAllCustomers();
}
