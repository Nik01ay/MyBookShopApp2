package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
        return new ArrayList<>();
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
    public String getSearchResult(Model model) {
        model.addAttribute("newBooks", bookService.getPageOfDateBooks(LocalDate.now().minusYears(100), LocalDate.now() , 0, 5).getContent());
        System.out.println(bookService.getPageOfDateBooks(LocalDate.now().minusYears(100), LocalDate.now(), 0, 5).getContent());
        return "books/recent";
    }

    /*
    @GetMapping(value = {"/books/recent"})
    //public String booksRecentPage(){
   //     return "books/recent";
   // }
    @ResponseBody
    public BookPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                         @RequestParam("limit") Integer limit,
                                         @PathVariable(value = "fromDate", required = false)  LocalDate fromDate,
                                         @PathVariable(value = "toDate", required = false) LocalDate toDate)
    {
        return new BookPageDto(bookService.getPageOfDateBooks(fromDate,toDate, offset, limit).getContent());
    }
*/




    @GetMapping("/books/popular")
    public String booksPopularPage(){
        return "books/popular";
    }

    @GetMapping("/genres/index")
    public String genresIndexPage(){
        return "genres/index";
    }

}
