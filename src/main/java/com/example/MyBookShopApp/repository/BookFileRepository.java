package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.file.BookFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookFileRepository extends JpaRepository<BookFileEntity, Integer> {

    public BookFileEntity findBookFileByHash(String hash);
}

