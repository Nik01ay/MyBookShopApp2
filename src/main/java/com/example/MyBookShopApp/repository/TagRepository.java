package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.other.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity,Integer> {
}
