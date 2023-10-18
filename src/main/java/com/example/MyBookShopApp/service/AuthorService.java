package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.AuthorEntity;
import com.example.MyBookShopApp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private  AuthorRepository authorRepository;


    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Map<String, List<AuthorEntity>> getAuthorsMap(){
        List<AuthorEntity> authors = authorRepository.findAll();
        System.out.println(authors.size());
        return authors.stream().collect(Collectors.groupingBy((AuthorEntity a)->{return a.getName().substring(0,1);}));
    }
    public AuthorEntity getAuthorBySlug(String slug){
        return authorRepository.findBySlug(slug);
    }
}

