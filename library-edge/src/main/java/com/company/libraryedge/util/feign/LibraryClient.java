package com.company.libraryedge.util.feign;

import com.company.libraryedge.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "book-service")
@RequestMapping("/books")
public interface LibraryClient {
    @GetMapping
    public List<Book> findAllBooks();

//    @GetMapping(value = "/{id}")
//    public Book findBookById(int id);

    @GetMapping(value = "/isbn/{isbn}")
    public Book findBookByIsbn(String isbn);
}
