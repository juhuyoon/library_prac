package com.company.librarybookbackingservice.controller;

import com.company.librarybookbackingservice.dao.BookDao;
import com.company.librarybookbackingservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    BookDao bookDao;

    /**
     * Posts a book to the database
     * @param book
     * @return Book
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        Book book1 = bookDao.createBook(book);
        return book1;
    }

    /**
     * Gets a book from the database by book id
     * @param id
     * @return book
     */

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable int id) {
        Book book = bookDao.getBook(id);
        return book;
    }

    /**
     * Get A List of Books
     * @return List<Book>
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        List<Book> bList = bookDao.getAllBooks();
        return bList;
    }

    /**
     * Gets a book by the isbn
     * @param isbn
     * @return book
     */
    @GetMapping("/isbn/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookByIsbn(@PathVariable String isbn) {
        Book book = bookDao.getBookByIsbn(isbn);
        return book;
    }

    /**
     * Updates book
     * @param id
     * @param book
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable int id, @RequestBody @Valid Book book) {
        bookDao.updateBook(book);
    }

    /**
     * Deletes book
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable int id) {
        bookDao.deleteBook(id);
    }
}
