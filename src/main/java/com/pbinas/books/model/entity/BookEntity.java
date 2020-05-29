package com.pbinas.books.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Entity
@Table(name = "BOOK")
@NoArgsConstructor
@Getter
@Setter
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String title;

    @ManyToMany
    private List<AuthorEntity> authors;

    private String description;

    private long isbn;

    private String language;

    private Year publicationYear;

    private String publisher;

    private LocalDate dateAdded;

    @ManyToMany
    private List<BookListEntity> lists;
}
