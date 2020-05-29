package com.pbinas.books.model.dto;

import com.pbinas.books.model.entity.BookListEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class UserTo {

    private long id;

    private String username;

    private String password;

    private List<BookListEntity> lists;
}
