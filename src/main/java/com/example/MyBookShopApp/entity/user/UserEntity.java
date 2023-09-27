package com.example.MyBookShopApp.entity.user;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.entity.book.file.FileDownloadEntity;
import com.example.MyBookShopApp.entity.book.review.BookReviewEntity;
import com.example.MyBookShopApp.entity.book.review.BookReviewLikeEntity;
import com.example.MyBookShopApp.entity.book.review.MessageEntity;
import com.example.MyBookShopApp.entity.payments.BalanceTransactionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @ManyToMany (mappedBy = "users")
    List<BookEntity> books;

    @Getter
    @Setter
    @OneToMany (mappedBy = "user")
    List<MessageEntity> messages;

    @Getter
    @Setter
    @OneToMany (mappedBy = "user")
    List<BookReviewLikeEntity> reviewlikes;

    @Getter
    @Setter
    @OneToMany (mappedBy = "user")
    List<BookReviewEntity> reviews;

    @Getter
    @Setter
    @OneToMany (mappedBy = "user")
    List<FileDownloadEntity> fileDownloads;

    @Getter
    @Setter
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    List<BalanceTransactionEntity> balanceTransactions;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserContactEntity contact;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime regTime;

    @Column(columnDefinition = "INT NOT NULL")
    private int balance;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
