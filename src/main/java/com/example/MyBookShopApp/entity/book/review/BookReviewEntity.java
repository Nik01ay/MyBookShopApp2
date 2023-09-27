package com.example.MyBookShopApp.entity.book.review;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.entity.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_review")
public class BookReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;
    //@Column(columnDefinition = "INT NOT NULL")
    //private int bookId;


    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
  //  @Column(columnDefinition = "INT NOT NULL")
   // private int userId;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
     public int getBookId() {
         return bookId;
     }

     public void setBookId(int bookId) {
         this.bookId = bookId;
     }

     public int getUserId() {
         return userId;
     }

     public void setUserId(int userId) {
         this.userId = userId;
     }
 */
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
