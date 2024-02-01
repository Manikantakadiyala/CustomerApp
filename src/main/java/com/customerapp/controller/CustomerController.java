package com.customerapp.controller;

import com.customerapp.entity.Customer;
import com.customerapp.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        customer = customerService.createCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id){
        return customerService.getCustomerById(id);
    }

//    @GetMapping
//    public List<Customer> getAllCustomers(){
//        return customerService.getAllCustomers();
//    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable String id){
        String msg = customerService.deleteCustomer(id);
        return msg;
    }
    @GetMapping
    public List<Customer> getAllCustomers(
            @RequestParam(name = "pageNO", required = false, defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = "firstName") String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        return customerService.getAllCustomers(pageNo, pageSize, sortBy, sortDir);
    }
}
