package com.customerapp.service;

import com.customerapp.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    Customer getCustomerById(String id);

    List<Customer> getAllCustomers();

    String deleteCustomer(String id);

    List<Customer> getAllCustomers(int pageNo, int pageSize, String sortBy, String sortDir);
}
