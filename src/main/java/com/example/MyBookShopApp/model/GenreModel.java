package com.example.MyBookShopApp.model;

import com.example.MyBookShopApp.entity.genre.GenreEntity;
import lombok.Getter;
import lombok.Setter;

public class GenreModel {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String slug;
    @Getter
    @Setter
    private Integer parentId;
    @Getter
    @Setter
    private Integer countBook;

    public static GenreModel toModel(GenreEntity entity) {
        GenreModel model = new GenreModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setParentId(entity.getParentId());
        model.setCountBook(entity.getBooks().size());
        model.setSlug(entity.getSlug());
        return model;
    }
}
