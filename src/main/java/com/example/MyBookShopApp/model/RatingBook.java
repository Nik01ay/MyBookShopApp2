package com.example.MyBookShopApp.model;

import lombok.Getter;
import lombok.Setter;

public class RatingBook {
    @Getter
    @Setter
    private Integer typeId;
    @Getter
    @Setter
    private Long count;

    public RatingBook() {
    }
    public RatingBook(Integer typeId, Long count) {
        this.typeId = typeId;
        this.count = count;
    }

}
