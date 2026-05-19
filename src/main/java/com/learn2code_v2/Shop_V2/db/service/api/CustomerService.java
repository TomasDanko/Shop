package com.learn2code_v2.Shop_V2.db.service.api;

import com.learn2code_v2.Shop_V2.domain.Customer;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    @Nullable
    Customer get(int id);

    @Nullable
    Integer addCustomer(Customer customer); //returns generated id

}
