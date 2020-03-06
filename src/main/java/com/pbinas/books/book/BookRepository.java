package com.pbinas.books.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    BookEntity findDistinctById(long id);

    @Override
    <S extends BookEntity> S save(S s);
}
