package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.model.BookPageDto;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("newBooks")
    public List<BookEntity> newBooks(){
        return  new ArrayList<>();
    }

    @ModelAttribute("booksList")
    public List<BookEntity> bookList(){
        return bookService.getBooksData();
    }

    @GetMapping("/books")
    public String booksPage(){
        return "books/index";
    }


    @GetMapping(value = {"/books/recent"})
    public String getRecentResult(Model model) {
        model.addAttribute("newBooks", bookService.getPageOfDateBooks(LocalDate.now().minusYears(100), LocalDate.now() , 0, 5).getContent());
        System.out.println(LocalDate.now().minusYears(100));
        System.out.println(LocalDate.now());
        System.out.println(bookService.getPageOfDateBooks(LocalDate.now().minusYears(100), LocalDate.now(), 0, 5).getContent());
        return "books/recent";
    }


    @GetMapping(value = {"/books/recent/page{from, to}" })//?from=06.09.2023&to=06.10.2023&offset=0&limit=20

    @ResponseBody
    public  BookPageDto getNextRecentPage(
            @RequestParam("from") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate fromDate,
            @RequestParam("to") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate toDate,
            @RequestParam(value = "offset", defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        return new BookPageDto(bookService.getPageOfDateBooks(fromDate, toDate, offset, limit).getContent());


    }





    @GetMapping("/books/popular")
    public String booksPopularPage(Model model,
                                   @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                   @RequestParam(value = "limit", defaultValue = "4") Integer limit)
    {
           BookPageDto bookDTO = new BookPageDto(bookService.getPageofPopularBooks(0.7, offset,limit).getContent());
            model.addAttribute("books", bookDTO.getBooks());
        return "books/popular";
    }

    @GetMapping("/genres/index")
    public String genresIndexPage(){



        return "genres/index";
    }

}
