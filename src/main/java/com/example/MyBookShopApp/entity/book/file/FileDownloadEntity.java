package com.example.MyBookShopApp.entity.book.file;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.entity.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "file_download")
public class FileDownloadEntity {

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

  /*  @Column(columnDefinition = "INT NOT NULL")
    private int userId;

    @Column(columnDefinition = "INT NOT NULL")
    private int bookId;
*/
    @Column(columnDefinition = "INT NOT NULL DEFAULT 1")
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  /*  public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
*/
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
