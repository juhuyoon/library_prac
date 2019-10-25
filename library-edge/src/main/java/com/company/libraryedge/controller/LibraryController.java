package com.company.libraryedge.controller;

import com.company.libraryedge.model.Book;
import com.company.libraryedge.model.CheckoutViewModel;
import com.company.libraryedge.service.LibraryServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    LibraryServiceLayer serviceLayer;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findAllBooks(){
        return serviceLayer.findAllBooks();
    }

    @GetMapping("/checkout")
    @ResponseStatus(HttpStatus.OK)
    public String checkoutBooks(@RequestBody @Valid CheckoutViewModel cvm){
        return serviceLayer.checkoutBooks(cvm);
    }
}
