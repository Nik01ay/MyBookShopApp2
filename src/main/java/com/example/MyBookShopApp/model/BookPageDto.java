package com.example.MyBookShopApp.model;

import com.example.MyBookShopApp.entity.book.BookEntity;

import java.util.List;

public class BookPageDto {

    private Integer count;
    private List<BookEntity> books;

    public BookPageDto(List<BookEntity> books) {
        this.count = books.size();
        this.books = books;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}
