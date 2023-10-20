package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.model.BookModel;
import com.example.MyBookShopApp.model.BookPageDto;
import com.example.MyBookShopApp.model.ResourceStorage;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Controller
public class BookController {

    private final BookService bookService;
    private final ResourceStorage storage;

    @Autowired
    public BookController(BookService bookService, ResourceStorage storage) {
        this.bookService = bookService;
        this.storage = storage;
    }
    @ModelAttribute("book")
    public BookModel book(){
        return  new BookModel();
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
    @GetMapping("/books/{slug}")
    public String getBookbySlug(Model model,
                                @PathVariable(name = "slug", required = true) String slug) {
        System.out.println(slug);
        model.addAttribute("book", BookModel.toModel(bookService.getBookBySlug(slug)));
        return "books/slug";
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

    @PostMapping("/books/{slug}/img/save")
    public String saveNewBookImage(@RequestParam("file") MultipartFile file, @PathVariable("slug")String slug) throws IOException {
        String savePath = storage.saveNewBookImage(file,slug);
        BookEntity bookToUpdate = bookService.getBookBySlug(slug);
        System.out.println("путь сохранения" + savePath);

        bookToUpdate.setImage(savePath);
        bookService.save(bookToUpdate); //save new path in db here

        return "redirect:/books/"+slug;
    }

    @GetMapping("/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash")String hash) throws IOException{
        Path path = storage.getBookFilePath(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file path: "+path);

        MediaType mediaType = storage.getBookFileMime(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file mime type: "+mediaType);

        byte[] data = storage.getBookFileByteArray(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file data len: "+data.length);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));
    }

}
