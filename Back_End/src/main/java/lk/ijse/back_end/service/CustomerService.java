package lk.ijse.back_end.service;

import lk.ijse.back_end.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void delete(Long id){
        customerRepository.deleteById(id);
    }
}