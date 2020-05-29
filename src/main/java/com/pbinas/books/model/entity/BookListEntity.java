package com.pbinas.books.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BOOKLIST")
@NoArgsConstructor
@Getter
@Setter
public class BookListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String title;

    private String description;

    @ManyToMany
    private List<BookEntity> books;
}
