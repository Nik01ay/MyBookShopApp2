package com.example.MyBookShopApp.entity.book.file;

import com.example.MyBookShopApp.data.BookFileType;
import com.example.MyBookShopApp.entity.book.BookEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "book_file")
public class BookFileEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;

    @Getter
    @Setter
    @Column(columnDefinition = "INT NOT NULL")
    private int typeId;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String path;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity book;
    public String getBookFileExtensionString(){
        return BookFileType.getExtensionStringByTypeID(typeId);
    }
}
