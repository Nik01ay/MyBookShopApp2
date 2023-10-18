package com.example.MyBookShopApp.model;

import com.example.MyBookShopApp.entity.AuthorEntity;
import lombok.Getter;
import lombok.Setter;

public class AuthorModel {

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
        private String descriptionStart;
        @Getter
        @Setter
        private String descriptionRest;
        @Getter
        @Setter
        private String photo;

        public static AuthorModel toModel(AuthorEntity entity) {
            AuthorModel model = new AuthorModel();
            model.setId(entity.getId());
            model.setName(entity.getName());
            int length = entity.getDescription().length();
            int trimIndex = length/8;

            model.setDescriptionStart(entity.getDescription().substring(0,trimIndex));
            model.setDescriptionRest(entity.getDescription().substring(trimIndex,length));

            model.setPhoto(entity.getPhoto());
            model.setSlug(entity.getSlug());

            return model;
        }
    }

