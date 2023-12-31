package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.model.BookModel;
import com.example.MyBookShopApp.model.BookPageDto;
import com.example.MyBookShopApp.model.SearchWordDto;
import com.example.MyBookShopApp.model.TagModel;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {

    private final BookService bookService;
    private final TagService tagService;

    @Autowired
    public MainPageController(BookService bookService, TagService tagService) {
        this.bookService = bookService;
        this.tagService = tagService;
    }

    @ModelAttribute("recommendedBooks")
    public List<BookModel> recommendedBooks() {
        return bookService.getBooksData().stream().map(BookModel::toModel).toList();
    }

    @ModelAttribute("newBooks")
    public List<BookModel> newBooks() {
        return bookService.getPageOfDateBooks(LocalDate.now().minusMonths(45), LocalDate.now(), 0, 5).getContent()
                .stream().map(BookModel::toModel).toList();
    }


    @ModelAttribute("popularBooks")
    public List<BookModel> popularBooks() {
        // bookService.calculateRatingAllBooksAndSave();
        return bookService.getPageofPopularBooks(0.5, 0, 5).getContent()
                .stream().map(BookModel::toModel).toList();

    }


    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults() {
        return new ArrayList<>();
    }


    @ModelAttribute("tagList")
    public List<TagModel> tagList() {
        return tagService.getFirst(40);

    }

    @GetMapping("/")
    public String mainPage() {
        // tagService.refreshAllTag();
        return "index.html";
    }


    @GetMapping("/postponed")
    public String postponedPage() {
        return "postponed";
    }

    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }

    @GetMapping("/signin")
    public String signinPage() {
        return "signin";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/faq")
    public String faqPage() {
        return "faq";
    }

    @GetMapping("/contacts")
    public String contactPage() {
        return "contacts";
    }

    @GetMapping("/documents/index")
    public String docIndexPage() {
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

    @GetMapping("/tags/{id}")
    public String getTagPage(@RequestParam(name = "offset", defaultValue = "0") Integer offset,
                                  @RequestParam(name = "limit", defaultValue = "5") Integer limit,
                                  @PathVariable("id") Integer id,
                             Model model) {
        System.out.println(id);
        model.addAttribute("tag", TagModel.toModel(tagService.getById(id)));
        model.addAttribute("books", bookService.getPageOfTagBooks(id, offset, limit).getContent());
        return "tags/index";
    }

}
