
        package lk.ijse.backend.Controller;



import lk.ijse.backend.DTO.CustomerDTO;


import lk.ijse.backend.ServiceLayer.CustomerService;

import lk.ijse.backend.Utill.APIResponse;

import lk.ijse.backend.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController

@CrossOrigin

@RequestMapping("/api/v1/Customer")

@RequiredArgsConstructor

public class CustomerController {



    private final CustomerService customerService;

    private final CustomerRepository customerRepository;




    @PostMapping

    public ResponseEntity<APIResponse<CustomerDTO>> saveCustomer(@RequestBody CustomerDTO customerDTO) {



        CustomerDTO saved = customerService.saveCustomer(customerDTO);

        return new ResponseEntity<>(new APIResponse<>(201, "Customer Saved", saved), HttpStatus.CREATED);

    }

// ===================== UPDATE CUSTOMER =====================

    @PutMapping

    public ResponseEntity<APIResponse<String>> updateCustomer(@RequestBody CustomerDTO customerDTO) {

        customerService.updateCustomer(customerDTO);

        return new ResponseEntity<>(new APIResponse<>(201, "Customer Updated", null), HttpStatus.CREATED);

    }



// ===================== DELETE CUSTOMER =====================

    @DeleteMapping("/{cid}")

    public ResponseEntity<APIResponse<String>> deleteCustomer(@PathVariable Long cid) {

        customerService.deleteCustomer(cid);

        return new ResponseEntity<>(new APIResponse<>(200, "Customer deleted successfully", null), HttpStatus.OK);

    }



// ===================== GET ALL CUSTOMERS =====================

    @GetMapping

    public ResponseEntity<APIResponse<List<CustomerDTO>>> getAllCustomers() {

        List<CustomerDTO> customers = customerService.getAllCustomers();

        return new ResponseEntity<>(new APIResponse<>(200, "Customers retrieved successfully", customers), HttpStatus.OK);

    }

    @GetMapping("/nextId")

    public ResponseEntity<APIResponse<Long>> getNextId() {

        Long nextId = customerRepository.findNextId();

        return new ResponseEntity<>(new APIResponse<>(200, "Next ID fetched", nextId), HttpStatus.OK);

    }

}