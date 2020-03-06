package com.pbinas.books.author;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    AuthorEntity findDistinctById(long id);

    @Override
    <S extends AuthorEntity> S save(S s);

    @Override
    <S extends AuthorEntity> List<S> saveAll(Iterable<S> iterable);
}
