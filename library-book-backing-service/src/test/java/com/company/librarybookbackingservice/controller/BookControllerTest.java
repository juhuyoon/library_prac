package com.company.librarybookbackingservice.controller;

import com.company.librarybookbackingservice.dao.BookDao;
import com.company.librarybookbackingservice.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookDao bookDao;

    @Autowired
    private BookController controller;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldCreateBookAndReturnBook() throws Exception {
        Book input = new Book();
        input.setIsbn("12345");
        input.setTitle("some book");
        input.setAuthor("some author");

        String inputJson = mapper.writeValueAsString(input);

        Book output = new Book();
        output.setId(1);
        output.setIsbn("12345");
        output.setTitle("some book");
        output.setAuthor("some author");

        String outputJson = mapper.writeValueAsString(output);

        when(bookDao.createBook(input)).thenReturn(output);

        this.mockMvc.perform(post("/books")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetBookAndReturnABook() throws Exception{
        Book output = new Book();
        output.setId(1);
        output.setIsbn("12345");
        output.setTitle("some book");
        output.setAuthor("some author");

        String outputJson = mapper.writeValueAsString(output);

        when(bookDao.getBook(1)).thenReturn(output);

        this.mockMvc.perform(get("/books/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetAllBooksAndReturnAListOfBooks() throws Exception {
        Book book1 = new Book();
        book1.setId(1);
        book1.setIsbn("12345");
        book1.setTitle("some book");
        book1.setAuthor("some author");

        Book book2 = new Book();
        book2.setId(1);
        book2.setIsbn("12345");
        book2.setTitle("some book");
        book2.setAuthor("some author");

        List<Book> bList = new ArrayList<>();
        bList.add(book1);
        bList.add(book2);

        String outputJson = mapper.writeValueAsString(bList);

        when(bookDao.getAllBooks()).thenReturn(bList);
        this.mockMvc.perform(get("/books"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));

    }

    @Test
    public void getBookByIsbnAndReturnABook() throws Exception {
        Book input = new Book();
        input.setIsbn("12345");
        input.setTitle("some book");
        input.setAuthor("some author");

        String inputJson = mapper.writeValueAsString(input);

        Book output = new Book();
        output.setId(1);
        output.setIsbn("12345");
        output.setTitle("some book");
        output.setAuthor("some author");

        String outputJson = mapper.writeValueAsString(output);

        when(bookDao.getBookByIsbn("12345")).thenReturn(output);

        this.mockMvc.perform(get("/books/isbn/12345"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldUpdateBookIsOk() throws Exception {
        Book input = new Book();
        input.setId(1);
        input.setIsbn("12345");
        input.setTitle("some book");
        input.setAuthor("some author");

        String inputJson = mapper.writeValueAsString(input);

        this.mockMvc.perform(put("/books/" + input.getId())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void shouldDeleteBookIsOk() throws Exception {
        controller.deleteBook(1);
        verify(bookDao, times(1)).deleteBook(1);

        this.mockMvc.perform(delete("/books/" +1))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(""));
    }
}