package com.company.librarybookbackingservice.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Book {
    /*
create table if not exists book (
	  id int not null auto_increment primary key,
    isbn varchar(20) not null,
    title varchar(20) not null,
    author varchar(20) not null
);
     */

    private int id;
    @NotNull(message = "please enter an isbn")
    private String isbn;
    @NotNull(message = "please enter a title")
    private String title;
    @NotNull(message= "please enter an author name")
    private String author;

    public Book() {
    }

    public Book(int id, String isbn, String title, String author) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
