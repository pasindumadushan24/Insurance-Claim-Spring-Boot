package lk.ijse.back_end.controller;

import lk.ijse.back_end.dto.APIResponse;
import lk.ijse.back_end.dto.CustomerDto;
import lk.ijse.back_end.entity.Customer;
import lk.ijse.back_end.service.CustomerServiceImpl;
import lk.ijse.back_end.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "http://localhost:63342")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerServiceImpl customerService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<APIResponse> saveCustomer(@RequestBody CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
        return ResponseEntity.ok(new APIResponse(200, "Customer Saved Successfully", null));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<APIResponse> getAllCustomers() {
        List<CustomerDto> customerDto = customerService.getAllCustomers();
        return ResponseEntity.ok(new APIResponse(200, "Success", customerDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}