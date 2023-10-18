package com.example.MyBookShopApp.model;

import com.example.MyBookShopApp.entity.other.TagEntity;
import lombok.Getter;
import lombok.Setter;

public class TagModel {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String type;


   // private static TagTypeRepository tagTypeRepository;

    public static TagModel toModel(TagEntity entity) {

        TagModel model = new TagModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
       // if (entity.getType().getId()!=0)
        model.setType(entity.getType().getName());
        /*if ((entity.getType() != null) || (entity.getType() != 0))  {
            model.setType(tagTypeRepository.getById(entity.getType()).getName());
        }*/
        return model;
    }
}
