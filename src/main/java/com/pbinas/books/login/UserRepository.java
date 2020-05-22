package com.pbinas.books.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findDistinctById(long id);

    UserEntity findUserEntityByUsername(String username);

    @Override
    List<UserEntity> findAll();

    @Override
    <S extends UserEntity> S save(S s);
}
