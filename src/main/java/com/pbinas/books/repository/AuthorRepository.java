package com.pbinas.books.repository;

import com.pbinas.books.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    AuthorEntity findDistinctById(long id);

    @Override
    <S extends AuthorEntity> S save(S s);

    @Override
    <S extends AuthorEntity> List<S> saveAll(Iterable<S> iterable);
}
