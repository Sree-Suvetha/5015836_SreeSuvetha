package com.example.BookStoreAPI.controller;

import com.example.BookStoreAPI.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customerList = new ArrayList<>();

    // POST Request: Create a new customer (Request Body as JSON)
    @PostMapping("/register")
    public Customer registerCustomer(@RequestBody Customer customer) {
        customer.setId((long) (customerList.size() + 1));  // Auto-generate customer ID
        customerList.add(customer);
        return customer;
    }

    // POST Request: Register a customer using form data
    @PostMapping("/register-form")
    public Customer registerCustomerFromForm(@RequestParam String name,
                                             @RequestParam String email,
                                             @RequestParam String password) {
        Customer customer = new Customer((long) (customerList.size() + 1), name, email, password);
        customerList.add(customer);
        return customer;
    }
}
