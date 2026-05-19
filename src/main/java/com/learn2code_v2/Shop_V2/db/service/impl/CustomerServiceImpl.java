package com.learn2code_v2.Shop_V2.db.service.impl;

import com.learn2code_v2.Shop_V2.db.repository.CustomerRepository;
import com.learn2code_v2.Shop_V2.db.service.api.CustomerService;
import com.learn2code_v2.Shop_V2.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getCustomers() {
         return customerRepository.getAllCustomers();
    }

    @Override
    public Customer get(int id) {
        return customerRepository.get(id);
    }

    @Override
    public Integer addCustomer(Customer customer) {
        return customerRepository.add(customer);
    }
}
