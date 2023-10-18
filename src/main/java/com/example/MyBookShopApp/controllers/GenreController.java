package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entity.genre.GenreEntity;
import com.example.MyBookShopApp.model.GenreModel;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GenreController {
    private final GenreService genreService;

    private final BookService bookService;

    @Autowired
    public GenreController(GenreService genreService, BookService bookService) {
        this.genreService = genreService;
        this.bookService = bookService;
    }

    @ModelAttribute("genres")
    public List<GenreEntity> genres(){
        return  new ArrayList<>();
    }

    @GetMapping("/genres/index")
        public String genresIndexPage(Model model){
         model.addAttribute("genres", genreService.getAll().stream().map(GenreModel::toModel).collect(Collectors.toList()));
        return "genres/index";
    }

    @GetMapping("/genres/{slug}")
    public String getGenrePage( @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                @RequestParam(value = "limit", defaultValue = "20") Integer limit,
                                         @PathVariable(value = "slug", required = false) String slug,
                                Model model) {
        model.addAttribute("books", bookService.getPageOfGenreBooks(slug,0, 5).getContent());
        return "/genres/slug";
    }


}
