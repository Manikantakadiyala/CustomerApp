package com.customerapp.service.impl;

import com.customerapp.Exception.ResourceNotFound;
import com.customerapp.entity.Customer;
import com.customerapp.repository.CustomerRepository;
import com.customerapp.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        String id = UUID.randomUUID().toString();
        customer.setId(id);
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Customer not found with id: " + id)
        );
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public String deleteCustomer(String id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
        return "customer with id: " + id + " deleted successfully";
    }

    @Override
    public List<Customer> getAllCustomers(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Customer> pageCustomers = customerRepository.findAll(pageable);
        List<Customer> customerList = pageCustomers.getContent();
        return customerList;
    }
}
