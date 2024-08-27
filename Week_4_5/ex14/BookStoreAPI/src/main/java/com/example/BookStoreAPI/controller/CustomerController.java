package com.example.BookStoreAPI.controller;

import com.example.BookStoreAPI.assembler.CustomerResourceAssembler;
import com.example.BookStoreAPI.model.Customer;
import com.example.BookStoreAPI.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerResourceAssembler customerResourceAssembler;

    @GetMapping
    public ResponseEntity<List<EntityModel<Customer>>> getAllCustomers() {
        List<EntityModel<Customer>> customers = customerService.findAllCustomers().stream()
                .map(customerResourceAssembler::toModel)
                .collect(Collectors.toList());
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Customer>> getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id)
                .map(customerResourceAssembler::toModel)
                .map(customerResource -> new ResponseEntity<>(customerResource, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Customer>> createCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        EntityModel<Customer> customerResource = customerResourceAssembler.toModel(savedCustomer);
        return new ResponseEntity<>(customerResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Customer>> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer updatedCustomer) {
        if (customerService.findCustomerById(id).isPresent()) {
            updatedCustomer.setId(id);
            Customer customer = customerService.saveCustomer(updatedCustomer);
            EntityModel<Customer> customerResource = customerResourceAssembler.toModel(customer);
            return new ResponseEntity<>(customerResource, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        if (customerService.findCustomerById(id).isPresent()) {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
