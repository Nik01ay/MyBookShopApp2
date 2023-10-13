package com.example.MyBookShopApp.model;

import com.example.MyBookShopApp.entity.book.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class BookPageDto {

    private Integer count;
    private List<BookModel> books;



    public BookPageDto(List<BookEntity> books) {
        this.count = books.size();
        List<BookModel> bookModels = new ArrayList<>();

        books
                .forEach(a->bookModels
                        .add(BookModel
                                .toModel(a)));
        this.books = bookModels;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<BookModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookModel> books) {
        this.books = books;
        this.count = books.size();
    }
}
