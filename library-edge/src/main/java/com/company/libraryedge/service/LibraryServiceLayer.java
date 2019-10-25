package com.company.libraryedge.service;

import com.company.libraryedge.model.Book;
import com.company.libraryedge.model.CheckoutViewModel;
import com.company.libraryedge.util.feign.LibraryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Component
public class LibraryServiceLayer {

    private LibraryClient client;

    @Autowired
    public LibraryServiceLayer(LibraryClient client) {
        this.client = client;
    }

    public List<Book> findAllBooks(){
        return client.findAllBooks();
    }

    public String checkoutBooks(@RequestBody @Valid CheckoutViewModel cvm){
        String message;
        switch (cvm.getDay()) {
            case "Monday":
            case "Wednesday":
            case "Friday":
                if (cvm.getIsbnList().size() > 3){
                    message = "Too many Books. Cannot checkout.";
                } else {
                    message ="Books Successfully Checked Out.";
                }
                break;
            case "Tuesday":
            case "Thursday":
                if (cvm.getIsbnList().size() > 2){
                    message = "Too many Books. Cannot checkout.";
                } else {
                    message ="Books Successfully Checked Out.";
                }
                break;
            case "Saturday":
                if (cvm.getIsbnList().size() > 4){
                    message = "Too many Books. Cannot checkout.";
                } else {
                    message ="Books Successfully Checked Out.";
                }
                break;
            case "Sunday":
                if (cvm.getIsbnList().size() > 1){
                    message = "Too many Books. Cannot checkout.";
                } else {
                    message ="Books Successfully Checked Out.";
                }
                break;
            default:
                message = "Come back tomorrow.";
        }
        return message;
    }
}
