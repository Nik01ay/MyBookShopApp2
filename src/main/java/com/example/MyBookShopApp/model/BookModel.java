package com.example.MyBookShopApp.model;

import com.example.MyBookShopApp.entity.book.BookEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class BookModel {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private Integer priceOld;
    @Getter
    @Setter
    private Integer price;
    @Getter
    @Setter
    private String image;
    @Getter
    @Setter
    private Integer discount;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Integer isBestseller;
    @Getter
    @Setter
    private LocalDateTime pubDate;
    @Getter
    @Setter
    private String slug;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private Double raiting;
    @Getter
    @Setter
    private String authors;


    public static BookModel toModel(BookEntity entity) {
        BookModel model = new BookModel();
        model.setId(entity.getId());
        model.setPrice(entity.getPrice());
        model.setPriceOld(entity.getPriceOld());
        model.setDiscount(entity.getPriceOld());
        model.setDescription(entity.getDescription());
        model.setImage(entity.getImage());
        model.setIsBestseller(entity.getIsBestseller());
        model.setPubDate(entity.getPubDate());
        model.setRaiting(entity.getRaiting());
        model.setSlug(entity.getSlug());
        model.setTitle(entity.getTitle());
        model.setAuthors(entity.getAuthors().stream().findFirst().get().getName());
        return model;
    }
}
