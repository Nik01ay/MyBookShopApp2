package com.example.MyBookShopApp.entity;

import com.example.MyBookShopApp.entity.book.BookEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "authors")
public class AuthorEntity {
        @Getter
        @Setter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Getter
        @Setter
        @ManyToMany (mappedBy = "authors")
          List<BookEntity> books;

        @Getter
        @Setter
        @Column(columnDefinition = "VARCHAR(255)")
        private String photo;

        @Getter
        @Setter
        @Column(columnDefinition = "VARCHAR(255) NOT NULL")
        private String slug;

        @Getter
        @Setter
        @Column(columnDefinition = "VARCHAR(255) NOT NULL")
        private String name;

        @Getter
        @Setter
        @Column(columnDefinition = "TEXT")
        private String description;

        public AuthorEntity() {
        }
}

   /* private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}*/
