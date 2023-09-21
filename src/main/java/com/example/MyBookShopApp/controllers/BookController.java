package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.data.AuthorService;
import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("booksList")
    public List<Book> bookList(){
        return bookService.getBooksData();
    }

    @GetMapping("/books")
    public String booksPage(){
        return "books/index";
    }

    @GetMapping("/books/recent")
    public String booksRecentPage(){
        return "books/recent";
    }

    @GetMapping("/books/popular")
    public String booksPopularPage(){
        return "books/popular";
    }

    @GetMapping("/genres/index")
    public String genresIndexPage(){
        return "genres/index";
    }

}
