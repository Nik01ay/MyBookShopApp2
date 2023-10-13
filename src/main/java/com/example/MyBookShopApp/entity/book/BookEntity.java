package com.example.MyBookShopApp.entity.book;

import com.example.MyBookShopApp.entity.AuthorEntity;
import com.example.MyBookShopApp.entity.book.file.FileDownloadEntity;
import com.example.MyBookShopApp.entity.book.review.BookReviewEntity;
import com.example.MyBookShopApp.entity.genre.GenreEntity;
import com.example.MyBookShopApp.entity.payments.BalanceTransactionEntity;
import com.example.MyBookShopApp.entity.user.UserEntity;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("id generated by db automatically")
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
    @ApiModelProperty("book price without discount")
    private Integer priceOld;
    @Getter
    @Setter
    @ApiModelProperty("discount value for book")
    private Integer price;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(255)")
    @ApiModelProperty("book image")
    private String image;

    @Getter
    @Setter
    @Column(columnDefinition = "TINYINT")
    private Integer discount;

    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
    @ApiModelProperty("book description text")
    private String description;

    @Getter
    @Setter
    @Column(name = "is_bestseller", columnDefinition = "TINYINT")
    @ApiModelProperty("if isBestseller = 1 so the book is considered to be bestseller and if 0 the book is not a " +
            "bestseller")

    private Integer isBestseller;


    @Getter
    @Setter

   // @JsonSerialize(using = LocalDateTimeSerializer.class)
   // @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    //@JsonFormat
      //      (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @ApiModelProperty("date of book publication")
    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime pubDate;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(255)")
    @ApiModelProperty("mnemonical identity sequence of characters")
    private String slug;
    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(255)")
    @ApiModelProperty("book title")
    private String title;

    @Getter
    @Setter
    @Column(columnDefinition = "FLOAT")
    @ApiModelProperty("raiting book in magazine")
    private Double raiting;






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