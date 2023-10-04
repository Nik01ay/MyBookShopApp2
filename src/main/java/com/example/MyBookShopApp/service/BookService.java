package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;


    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getBooksData() {
        return bookRepository.findAll();
    }


 /*   public List<BookEntity> getBooksByAuthor(String authorName) {
        return bookRepository.findBooksByAuthorNameContaining(authorName);
    }

  */

    public List<BookEntity> getBooksWithPriceBetween(Integer min, Integer max) {
        return bookRepository.findBooksByPriceOldBetween(min, max);
    }
    public List<BookEntity> getBooksByTitle(String title){
        return bookRepository.findBooksByTitleContaining(title);
    }
    public  List<BookEntity> getBooksWithPrice(Integer price) {
        return bookRepository.findBooksByPriceOldIs(price);
    }

    public List<BookEntity> getBooksWithMaxPrice() {
        return bookRepository.getBooksWithMaxDiscount();
    }

   /* public List<BookEntity> getBooksBestsellers() {
        return bookRepository.getBestsellers();
    }
    */

    public Page<BookEntity> getPageofRecommendedBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<BookEntity> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);
        return bookRepository.findBooksByTitleContaining(searchWord,  nextPage);
    }
    public Page<BookEntity> getPageOfDateBooks(LocalDate fromDate, LocalDate toDate , Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset,limit);

        return bookRepository.findByPubDateBetween(fromDate.atStartOfDay(), toDate.atStartOfDay(), nextPage);
    }


}
