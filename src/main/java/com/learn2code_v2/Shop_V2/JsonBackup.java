package com.learn2code_v2.Shop_V2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn2code_v2.Shop_V2.db.repository.CustomerRepository;
import com.learn2code_v2.Shop_V2.domain.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class JsonBackup implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public JsonBackup(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        List<Customer> backupCustomer = customerRepository.getAllCustomers();
        System.out.println("Nacitanie vsetkych customerov: " + backupCustomer.size());

        ObjectMapper objectMapper = new ObjectMapper();
        File backupFile = new File("backup/customer_backup.json");
        backupFile.getParentFile().mkdirs();
        objectMapper.writeValue(backupFile, backupCustomer);

        System.out.println("Zaloha ulozena do suboru: " + backupFile.getAbsolutePath());

    }
}
