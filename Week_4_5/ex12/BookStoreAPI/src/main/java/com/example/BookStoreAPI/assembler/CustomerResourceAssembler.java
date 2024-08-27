package com.example.BookStoreAPI.assembler;

import com.example.BookStoreAPI.controller.CustomerController;
import com.example.BookStoreAPI.model.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomerResourceAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {

    @Override
    public EntityModel<Customer> toModel(Customer customer) {
        // Create an EntityModel to hold the customer entity
        EntityModel<Customer> customerResource = EntityModel.of(customer);

        // Add links to the resource
        customerResource.add(
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel()
        );

        return customerResource;
    }
}
