package com.pbinas.books.model.dto;

import com.pbinas.books.model.entity.AuthorEntity;
import com.pbinas.books.model.entity.BookListEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Data
@NoArgsConstructor
public class BookTo {

    private long id;

    private String title;

    private List<AuthorEntity> authors;

    private String description;

    private long isbn;

    private String language;

    private Year publicationYear;

    private String publisher;

    private LocalDate dateAdded;

    private List<String> lists;
}
