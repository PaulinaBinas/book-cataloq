package com.pbinas.books.repository;

import com.pbinas.books.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    BookEntity findDistinctById(long id);

    @Override
    <S extends BookEntity> S save(S s);
}
