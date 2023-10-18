package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.genre.GenreEntity;
import com.example.MyBookShopApp.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;

    }
    public List<GenreEntity> getAll(){
        return genreRepository.findAll();
    }
}
