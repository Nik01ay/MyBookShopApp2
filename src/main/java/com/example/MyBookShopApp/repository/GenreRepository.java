package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.genre.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity,Integer> {
}
