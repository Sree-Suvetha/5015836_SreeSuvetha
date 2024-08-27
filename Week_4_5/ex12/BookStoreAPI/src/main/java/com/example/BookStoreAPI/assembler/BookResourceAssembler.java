package com.example.BookStoreAPI.assembler;

import com.example.BookStoreAPI.controller.BookController;
import com.example.BookStoreAPI.model.Book;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class BookResourceAssembler implements RepresentationModelAssembler<Book, EntityModel<Book>> {

    @Override
    public EntityModel<Book> toModel(Book book) {
        // Create an EntityModel to hold the book entity
        EntityModel<Book> bookResource = EntityModel.of(book);

        // Add links to the resource
        bookResource.add(
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(book.getId())).withSelfRel()
        );

        return bookResource;
    }
}
