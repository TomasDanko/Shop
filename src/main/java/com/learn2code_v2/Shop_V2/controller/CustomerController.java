package com.learn2code_v2.Shop_V2.controller;

import com.learn2code_v2.Shop_V2.db.service.api.CustomerService;
import com.learn2code_v2.Shop_V2.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") int id){
        Customer customer = customerService.get(id);
        if(customer == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getAll(){
        List<Customer> customers = customerService.getCustomers();
         return new  ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Customer customer){
        Integer id = customerService.addCustomer(customer);
        if(id != null){
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
