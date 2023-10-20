package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.model.RatingBook;
import com.example.MyBookShopApp.repository.Book2UserRepository;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private BookRepository bookRepository;
    private Book2UserRepository book2UserRepository;

    private TagRepository tagRepository;

    @Autowired
    public BookService(BookRepository bookRepository, Book2UserRepository book2UserRepository, TagRepository tagRepository) {
        this.bookRepository = bookRepository;
        this.book2UserRepository = book2UserRepository;
        this.tagRepository = tagRepository;
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

    public BookEntity  getBookBySlug(String slug){
        return bookRepository.findBooksBySlug(slug);
    }

    public List<BookEntity> getBooksByTitle(String title) {
        return bookRepository.findBooksByTitleContaining(title);
    }

    public List<BookEntity> getBooksWithPrice(Integer price) {
        return bookRepository.findBooksByPriceOldIs(price);
    }

    public List<BookEntity> getBooksWithMaxPrice() {
        return bookRepository.getBooksWithMaxDiscount();
    }

   /* public List<BookEntity> getBooksBestsellers() {
        return bookRepository.getBestsellers();
    }
    */

    public Page<BookEntity> getPageofRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<BookEntity> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBooksByTitleContaining(searchWord, nextPage);
    }

    public Page<BookEntity> getPageOfDateBooks(LocalDate fromDate, LocalDate toDate, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);

        return bookRepository.findByPubDateBetween(fromDate.atStartOfDay(), toDate.atStartOfDay(), nextPage);
    }

    public Page<BookEntity> getPageOfGenreBooks(String  genreSlug,  Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);

        return bookRepository.findByGenreSlug(genreSlug, nextPage);
    }
    public Page<BookEntity> getPageOfAuthorSlugBooks(String  authorSlug,  Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);

        return bookRepository.findByAuthorSlug(authorSlug, nextPage);
    }


    public Page<BookEntity> getPageofPopularBooks(Double minRaiting, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findByRaitingGreaterThan(minRaiting,nextPage);
    }
    public Map<Integer, Double> calculateRatingAllBooksAndSave() {
        Map<Integer, Double> result = new HashMap<>();
        book2UserRepository.findDistinctBookIds().forEach((bookId) -> {
            double raiting = calculateRatingBookById(bookId);
            result.put(bookId, raiting);
            BookEntity book =  bookRepository.getById(bookId);
            book.setRaiting(raiting);
            bookRepository.save(book);
        });
        return result;
    }

    public double calculateRatingBookById(Integer bookId) {



            List<Object[]> results = book2UserRepository.getTypeCountsByBookId(bookId);
            List<RatingBook> ratingBooks = new ArrayList<>();

            for (Object[] result : results) {
                Integer typeId = (Integer) result[0];
                Long count = (Long) result[1];
                RatingBook ratingBook = new RatingBook(typeId, count);
                ratingBooks.add(ratingBook);
            }



        return calculateRatingByMap(ratingBooks);
    }

    public double calculateRatingByMap(List<RatingBook> rbList) {

        var ref = new Object() {
            double result = 0;
        };
        rbList.forEach((rb) -> {
                    switch (rb.getTypeId()) {
                        case 1 -> ref.result += rb.getCount() * 0.4;
                        case 2 -> ref.result += rb.getCount() * 0.7;
                        case 3 -> ref.result += rb.getCount();
                    }
                }
        );
        return ref.result;
    }

    public Page<BookEntity> getPageOfTagBooks(Integer tagId, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit);
        String tagName = tagRepository.findById(tagId).get().getName();
        return bookRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(tagName,tagName, nextPage);
    }

    public void save(BookEntity book){
        bookRepository.save(book);
    }

}

