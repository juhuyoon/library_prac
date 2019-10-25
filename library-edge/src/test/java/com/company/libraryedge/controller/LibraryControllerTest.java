package com.company.libraryedge.controller;

import com.company.libraryedge.model.Book;
import com.company.libraryedge.model.CheckoutViewModel;
import com.company.libraryedge.service.LibraryServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LibraryController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void findAllBooks() throws Exception{
        Book book1 = new Book(1, "111", "myTitle", "myAuthor");
        Book book2 = new Book(2, "222", "myTitle1", "myAuthor1");
        Book book3 = new Book(3, "333", "myTitle2", "myAuthor2");
        Book book4 = new Book(4, "444", "myTitle1", "myAuthor1");
        Book book5 = new Book(5, "555", "myTitle2", "myAuthor2");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        when(serviceLayer.findAllBooks()).thenReturn(books);

        List<Book> listChecker = new ArrayList<>();
        listChecker.addAll(books);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/library"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void checkoutBooks() throws Exception{
        CheckoutViewModel cvm = new CheckoutViewModel();
        List<String> isbnList = new ArrayList<>();
        isbnList.add("111");
        isbnList.add("222");
        isbnList.add("333");
        cvm.setIsbnList(isbnList);
        cvm.setDay("Monday");
        String inputJson = mapper.writeValueAsString(cvm);
        String outputString = "Books Successfully Checked Out.";
        String outputJson = "Books Successfully Checked Out.";

        when(serviceLayer.checkoutBooks(cvm)).thenReturn(outputString);

        this.mockMvc.perform(get("/library/checkout")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
}