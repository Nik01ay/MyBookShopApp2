package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.links.Book2UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Book2UserRepository  extends JpaRepository<Book2UserEntity, Integer> {
    //List<Book2UserEntity> finByBookId(Integer bookId);

    @Query("SELECT DISTINCT b.bookId FROM Book2UserEntity b")
    List<Integer> findDistinctBookIds();





    @Query("SELECT b.typeId, COUNT(b.typeId) FROM Book2UserEntity b WHERE b.bookId = :book_id GROUP BY b.typeId")
    List<Object[]> getTypeCountsByBookId(@Param("book_id") int bookId);


    // Map<Integer, Long> getTypeCountsByBookId(@Param("book_id") int bookId);

    //@Query("SELECT b.book_id, NEW Map(b.type_id, COUNT(b.type_id)) FROM Book2UserEntity b GROUP BY b.book_id, b.type_id")
    //HashMap<Integer, Map<Integer, Long>> getTypeCountsByBook();
}


