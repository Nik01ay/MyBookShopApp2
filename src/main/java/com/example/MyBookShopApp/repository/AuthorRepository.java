package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {


     AuthorEntity findByName(String name);
     AuthorEntity findBySlug(String slug);

   // List<AuthorEntity> getAuthorEntityList();

    /*
    default Map<String, List<AuthorEntity>> findAllGroupedByFirstLetter() {
        List<AuthorEntity> authors = findAll();
        return authors.stream()
                .collect(Collectors.groupingBy(author -> author.getName().substring(0, 1)));
    }

     */


    /*
    @Query("SELECT SUBSTRING(a.name, 1, 1) AS firstLetter, a FROM AuthorEntity a GROUP BY firstLetter")
    List<Object[]> findAuthorsGroupedByFirstLetter();

     */
}



