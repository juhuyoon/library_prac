package com.company.librarybookbackingservice.dao;

import com.company.librarybookbackingservice.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {

    @Autowired
    BookDao bookDao;

    private Book book1, book2;

    @Before
    public void setUp() throws Exception {
        bookDao.getAllBooks().forEach(b ->
        {
            bookDao.deleteBook(b.getId());
        });

        book1 = new Book();
        book1.setIsbn("1231234");
        book1.setTitle("Some Book");
        book1.setAuthor("Some author");

        book2 = new Book();
        book2.setIsbn("09890890");
        book2.setTitle("some other book");
        book2.setAuthor("some other author");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldCreateBook() {
        book1 = bookDao.createBook(book1);
        assertEquals(1, bookDao.getAllBooks().size());
    }

    @Test
    public void getBook() {
        book1 = bookDao.createBook(book1);
        Book fromDao = bookDao.getBook(book1.getId());

        assertEquals(book1, fromDao);
    }

    @Test
    public void getAllBooks() {
        book1 = bookDao.createBook(book1);
        book2 = bookDao.createBook(book2);

        List<Book> bList = new ArrayList<>();
        bList.add(book1);
        bList.add(book2);

        assertEquals(2, bList.size());
    }

    @Test
    public void getBookByIsbn() {
        book1 = bookDao.createBook(book1);
        Book fromDao = bookDao.getBookByIsbn(book1.getIsbn());

        assertEquals(book1, fromDao);
    }

    @Test
    public void updateBook() {
        book1 = bookDao.createBook(book1);
        book1.setTitle("UPDATED BOOK");

        Book fromDao = bookDao.getBook(book1.getId());
        assertNotEquals(book1, fromDao);
    }

    @Test
    public void deleteBook() {
        book1 = bookDao.createBook(book1);
        bookDao.deleteBook(book1.getId());

        Book fromDao = bookDao.getBook(book1.getId());

        assertNull(fromDao);
    }
}