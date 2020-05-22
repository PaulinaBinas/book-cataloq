package com.pbinas.books.list;

import com.pbinas.books.book.BookEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.ArrayList;
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
