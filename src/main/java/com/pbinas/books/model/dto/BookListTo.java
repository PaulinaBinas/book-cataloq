package com.pbinas.books.model.dto;

import com.pbinas.books.model.entity.BookEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class BookListTo {

    private long id;

    private String title;

    private String description;

    private List<String> books;
}
