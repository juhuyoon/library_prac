package com.company.libraryedge.service;

import com.company.libraryedge.model.Book;
import com.company.libraryedge.model.CheckoutViewModel;
import com.company.libraryedge.util.feign.LibraryClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceLayerTest {
    private LibraryClient client;
    private LibraryServiceLayer serviceLayer;

    private void setUpLibraryClientMock(){
        client = mock(LibraryClient.class);
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

        doReturn(book1).when(client).findBookByIsbn("111");
        doReturn(book2).when(client).findBookByIsbn("222");
        doReturn(book3).when(client).findBookByIsbn("333");
        doReturn(book4).when(client).findBookByIsbn("444");
        doReturn(book5).when(client).findBookByIsbn("555");
        doReturn(books).when(client).findAllBooks();
    }

    @BeforeEach
    void setUp() {
        setUpLibraryClientMock();
        serviceLayer = new LibraryServiceLayer(client);
    }

    @Test
    void findAllBooksShouldReturnListOfAllBooksInInventory() {
        //We have arranged 5 books in our client mock
        //test verifies that servicelayer returns a list of 5 books
        assertEquals(5, serviceLayer.findAllBooks().size());
    }

    @Test
    void checkoutBooksCheckOut3OnMondayShouldRespondWithSuccessString() {
        CheckoutViewModel cvm = new CheckoutViewModel();
        List<String> isbnList = new ArrayList<>();
        isbnList.add("111");
        isbnList.add("222");
        isbnList.add("333");
        cvm.setIsbnList(isbnList);
        cvm.setDay("Monday");

        String response = serviceLayer.checkoutBooks(cvm);
        assertEquals("Books Successfully Checked Out.", response);
    }

    @Test
    void checkoutBooksCheckOut3OnWednesdayShouldRespondWithSuccessString() {
        CheckoutViewModel cvm = new CheckoutViewModel();
        List<String> isbnList = new ArrayList<>();
        isbnList.add("111");
        isbnList.add("222");
        isbnList.add("333");
        cvm.setIsbnList(isbnList);
        cvm.setDay("Wednesday");

        String response = serviceLayer.checkoutBooks(cvm);
        assertEquals("Books Successfully Checked Out.", response);
    }

    @Test
    void checkoutBooksCheckOut3OnFridayShouldRespondWithSuccessString() {
        CheckoutViewModel cvm = new CheckoutViewModel();
        List<String> isbnList = new ArrayList<>();
        isbnList.add("111");
        isbnList.add("222");
        isbnList.add("333");
        cvm.setIsbnList(isbnList);
        cvm.setDay("Friday");

        String response = serviceLayer.checkoutBooks(cvm);
        assertEquals("Books Successfully Checked Out." ,response);
    }

    @Test
    void checkoutBooksCheckOutOver3OnMondayWednesdayOrFridayShouldRespondWithFailureString() {
        CheckoutViewModel cvm = new CheckoutViewModel();
        List<String> isbnList = new ArrayList<>();
        isbnList.add("111");
        isbnList.add("222");
        isbnList.add("333");
        isbnList.add("444");
        cvm.setIsbnList(isbnList);
        cvm.setDay("Monday");

        String response = serviceLayer.checkoutBooks(cvm);
        assertEquals("Too many Books. Cannot checkout." ,response);

        cvm.setDay("Wednesday");
        response = serviceLayer.checkoutBooks(cvm);
        assertEquals("Too many Books. Cannot checkout." ,response);

        cvm.setDay("Friday");
        response = serviceLayer.checkoutBooks(cvm);
        assertEquals("Too many Books. Cannot checkout." ,response);
    }

    @Test
    void checkoutBooksCheckOut2OnTuesdayShouldRespondWithSuccessString() {
        CheckoutViewModel cvm = new CheckoutViewModel();
        List<String> isbnList = new ArrayList<>();
        isbnList.add("111");
        isbnList.add("222");
        cvm.setIsbnList(isbnList);
        cvm.setDay("Tuesday");

        String response = serviceLayer.checkoutBooks(cvm);
        assertEquals("Books Successfully Checked Out." ,response);
    }

    @Test
    void checkoutBooksCheckOut2OnThursdayShouldRespondWithSuccessString() {
        CheckoutViewModel cvm = new CheckoutViewModel();
        List<String> isbnList = new ArrayList<>();
        isbnList.add("111");
        isbnList.add("222");
        cvm.setIsbnList(isbnList);
        cvm.setDay("Thursday");

        String response = serviceLayer.checkoutBooks(cvm);
        assertEquals("Books Successfully Checked Out." ,response);
    }

    @Test
    void checkoutBooksCheckOutOver2OnTuesdayOrThursdayShouldRespondWithFailureString() {
        CheckoutViewModel cvm = new CheckoutViewModel();
        List<String> isbnList = new ArrayList<>();
        isbnList.add("111");
        isbnList.add("222");
        isbnList.add("333");
        cvm.setIsbnList(isbnList);
        cvm.setDay("Tuesday");

        String response = serviceLayer.checkoutBooks(cvm);
        assertEquals(response, "Too many Books. Cannot checkout.");

        cvm.setDay("Thursday");
        response = serviceLayer.checkoutBooks(cvm);
        assertEquals("Too many Books. Cannot checkout." ,response);
    }

    @Test
    void checkoutBooksCheckOut4OnSaturdayShouldRespondWithSuccessString() {
        CheckoutViewModel cvm = new CheckoutViewModel();
        List<String> isbnList = new ArrayList<>();
        isbnList.add("111");
        isbnList.add("222");
        isbnList.add("333");
        isbnList.add("444");
        cvm.setIsbnList(isbnList);
        cvm.setDay("Saturday");

        String response = serviceLayer.checkoutBooks(cvm);
        assertEquals("Books Successfully Checked Out." ,response);
    }

    @Test
    void checkoutBooksCheckOutOver4OnSaturdayShouldRespondWithFailureString() {
        CheckoutViewModel cvm = new CheckoutViewModel();
        List<String> isbnList = new ArrayList<>();
        isbnList.add("111");
        isbnList.add("222");
        isbnList.add("333");
        isbnList.add("444");
        isbnList.add("555");
        cvm.setIsbnList(isbnList);
        cvm.setDay("Saturday");

        String response = serviceLayer.checkoutBooks(cvm);
        assertEquals("Too many Books. Cannot checkout." ,response);
    }

    @Test
    void checkoutBooksCheckOut1OnSundayShouldRespondWithSuccessString() {
        CheckoutViewModel cvm = new CheckoutViewModel();
        List<String> isbnList = new ArrayList<>();
        isbnList.add("111");
        cvm.setIsbnList(isbnList);
        cvm.setDay("Sunday");

        String response = serviceLayer.checkoutBooks(cvm);
        assertEquals("Books Successfully Checked Out." ,response);
    }

    @Test
    void checkoutBooksCheckOutOver1OnSundayShouldRespondWithFailureString() {
        CheckoutViewModel cvm = new CheckoutViewModel();
        List<String> isbnList = new ArrayList<>();
        isbnList.add("111");
        isbnList.add("222");
        cvm.setIsbnList(isbnList);
        cvm.setDay("Sunday");

        String response = serviceLayer.checkoutBooks(cvm);
        assertEquals("Too many Books. Cannot checkout." ,response);
    }
}