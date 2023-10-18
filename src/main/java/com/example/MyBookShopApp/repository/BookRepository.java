package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity,Integer> {

   // List<BookEntity> findBooksByAuthorNameContaining(String name);
   BookEntity findBooksBySlug (String slug);
    List<BookEntity> findBooksByTitleContaining(String bookTitle);

    Page<BookEntity>
        findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String tag,String tag2, Pageable nextPage);

   // List<BookEntity> findBooksByTitleContainingOrFindByDescriptionContaining(String tagName);

    // Page<BookEntity> findBooksByTitleContainingOrFindByDescriptionContaining(String tagName, Pageable nextPage);

    List<BookEntity> findBooksByPriceOldBetween(Integer min, Integer max);

    List<BookEntity> findBooksByPriceOldIs(Integer price);
/*@Query("select * from books where is_bestseller = 1")
    List<BookEntity> getBestsellers ();
*/
@Query(value = "SELECT * FROM books WHERE discount=(SELECT MAX(discount) FROM books)", nativeQuery = true)
List<BookEntity> getBooksWithMaxDiscount();

    Page<BookEntity> findBooksByTitleContaining(String bookTitle, Pageable nextPage);

    Page<BookEntity>  findByPubDateBetween(LocalDateTime fromDate, LocalDateTime toDate, Pageable nextPage);
    @Query(value = "SELECT * FROM books WHERE discount=(SELECT MAX(discount) FROM books)", nativeQuery = true)
    Page<BookEntity> getBooksWithMaxDiscount(Pageable nextPage);


    Page<BookEntity> findByRaitingGreaterThan(Double minRating, Pageable pageable);

    @Query("SELECT b FROM BookEntity b JOIN b.genres g WHERE g.slug = :slug")
    Page<BookEntity>  findByGenreSlug(@Param("slug") String genreSlug, Pageable nextPage);

    @Query("SELECT b FROM BookEntity b JOIN b.authors a WHERE a.slug = :slug")
    Page<BookEntity>  findByAuthorSlug(@Param("slug") String genreSlug, Pageable nextPage);


 /*   @Query("from Book")
    List<BookEntity> customFindAllBooks();

  */
}
