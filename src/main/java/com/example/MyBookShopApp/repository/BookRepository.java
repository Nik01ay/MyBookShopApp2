package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,Integer> {

  /*  List<BookEntity> findBooksByAuthorId(String name);*/

 /*   @Query("from Book")
    List<BookEntity> customFindAllBooks();

  */
}
