package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entity.AuthorEntity;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.model.AuthorModel;
import com.example.MyBookShopApp.model.BookPageDto;
import com.example.MyBookShopApp.service.AuthorService;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AuthorsController {

    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AuthorsController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ModelAttribute("author")
    public AuthorModel author() {
        return new AuthorModel();
    }

    @ModelAttribute("books")
    public List<BookEntity> books()
    {
        return new ArrayList<>();
    }
    @ModelAttribute("authorsMap")
    public Map<String, List<AuthorEntity>> authorsMap() {

        return authorService.getAuthorsMap();
    }

    @GetMapping("/authors")
    public String authorsPage() {
        return "authors/index";
    }

    @GetMapping("/authors/{slug}")
    public String getAuthorPage(Model model,
                                @PathVariable(name = "slug", required = true) String slug,
                                @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
       model.addAttribute("books", new BookPageDto(bookService.getPageOfAuthorSlugBooks(slug, offset, limit).getContent()).getBooks());
       model.addAttribute("author", AuthorModel.toModel (authorService.getAuthorBySlug(slug)));
        return "/authors/slug";
    }
}
