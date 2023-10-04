package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.model.BookPageDto;
import com.example.MyBookShopApp.model.SearchWordDto;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {

    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<BookEntity> recommendedBooks(){
        return bookService.getBooksData();
    }


    @ModelAttribute("popularBooks")
    public List<BookEntity> popularBooks(){
        return bookService.getBooksData();
    }


    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults() {
        return new ArrayList<>();
    }


    @GetMapping("/")
    public String mainPage(){
        return "index";
    }


    @GetMapping("/postponed")
    public String postponedPage(){
        return "postponed";
    }
    @GetMapping("/cart")
    public String cartPage(){
        return "cart";
    }
    @GetMapping("/signin")
    public String signinPage(){
        return "signin";
    }
    @GetMapping("/about")
    public String aboutPage(){
        return "about";
    }
    @GetMapping("/faq")
    public String faqPage(){
        return "faq";
    }

    @GetMapping("/contacts")
    public String contactPage(){
        return "contacts";
    }

    @GetMapping("/documents/index")
    public String docIndexPage(){
        return "/documents/index";
    }


    @GetMapping("/books/recommended")
    @ResponseBody
    public BookPageDto getBooksPage(@RequestParam("offset") Integer offset,
                                    @RequestParam("limit") Integer limit) {
        return new BookPageDto(bookService.getPageofRecommendedBooks(offset, limit).getContent());
    }

    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResult(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
                                  Model model) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5).getContent());
        return "/search/index";
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BookPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit,
                                          @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto) {
        return new BookPageDto(bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit).getContent());
    }


}
