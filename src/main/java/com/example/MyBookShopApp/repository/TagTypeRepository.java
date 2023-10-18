package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.other.TagTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagTypeRepository extends JpaRepository<TagTypeEntity,Integer> {
}
