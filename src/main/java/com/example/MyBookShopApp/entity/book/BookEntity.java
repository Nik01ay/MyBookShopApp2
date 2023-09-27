package com.example.MyBookShopApp.entity.book;

import com.example.MyBookShopApp.entity.AuthorEntity;
import com.example.MyBookShopApp.entity.book.file.FileDownloadEntity;
import com.example.MyBookShopApp.entity.book.review.BookReviewEntity;
import com.example.MyBookShopApp.entity.genre.GenreEntity;
import com.example.MyBookShopApp.entity.payments.BalanceTransactionEntity;
import com.example.MyBookShopApp.entity.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "books")
public class BookEntity {


    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "book2author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    List<AuthorEntity> authors;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "book2genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    List<GenreEntity> genres;


    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "book2user",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<UserEntity> users;

    @Getter
    @Setter
    @OneToMany (mappedBy = "book")
    List<BookReviewEntity> reviews;

    @Getter
    @Setter
    @OneToMany (mappedBy = "book")
    List<FileDownloadEntity> fileDownloads;


    @Getter
    @Setter
    @OneToMany (mappedBy = "book")
    List<BalanceTransactionEntity> balanceTransactions;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   /* @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private AuthorEntity author;
*/

    // todo в схеме нет price_old


    @Getter
    @Setter
    @Column(name = "price_old")
    private Integer priceOld;
    @Getter
    @Setter
    private Integer price;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(255)")
    private String image;

    @Getter
    @Setter
    @Column(columnDefinition = "TINYINT")
    private Integer discount;

    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
    private String description;

    @Getter
    @Setter
    @Column(name = "is_bestseller", columnDefinition = "TINYINT")
    private Integer isBestseller;


    @Getter
    @Setter
    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime pub_date;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(255)")
    private String slug;
    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(255)")
    private String title;




    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
              //  ", author=" + author +
                ", title='" + title + '\'' +
                ", priceOld=" + priceOld +
                ", price=" + price +
                '}';
    }
}