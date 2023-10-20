package com.example.MyBookShopApp.model;

import com.example.MyBookShopApp.entity.book.file.BookFileEntity;
import lombok.Getter;
import lombok.Setter;

public class BookFileModel {

    @Getter
    @Setter
    private int id;


    @Getter
    @Setter
    private String hash;

    @Getter
    @Setter
    private int typeId;

    @Getter
    @Setter
    private String path;
    @Getter
    @Setter
    private String extension;

    public static BookFileModel toModel(BookFileEntity entity) {
        BookFileModel model = new BookFileModel();
        if (entity != null) {
            model.setId(entity.getId());
            model.setHash(entity.getHash());
            model.setPath(entity.getPath());
            model.setTypeId(entity.getTypeId());
           model.setExtension(entity.getBookFileExtensionString());
        }
        return model;
    }
}