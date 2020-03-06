package com.pbinas.books.list;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookListRepository extends JpaRepository<BookListEntity, Long> {

    BookListEntity findDistinctById(long id);

    @Override
    <S extends BookListEntity> S save(S s);
}
