package com.company.librarybookbackingservice.dao;

import com.company.librarybookbackingservice.model.Book;

import java.util.List;

public interface BookDao {
    // DAO methods
    public Book createBook(Book book);
    public Book getBook(int id);
    public List<Book> getAllBooks();
    public Book getBookByIsbn(String isbn);
    public void updateBook(Book book);
    public void deleteBook(int id);

}
