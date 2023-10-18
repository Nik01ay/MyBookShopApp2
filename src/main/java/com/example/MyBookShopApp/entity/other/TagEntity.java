package com.example.MyBookShopApp.entity.other;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tag")
public class TagEntity {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @Getter
    @Setter
    @Column
    private Long usability;

    @Getter
    @Setter

    @ManyToOne
    @JoinColumn(name = "type")
    private TagTypeEntity type;
}
