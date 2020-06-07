package com.pbinas.books.service.impl;

import com.pbinas.books.model.entity.AuthorEntity;
import com.pbinas.books.repository.AuthorRepository;
import com.pbinas.books.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void save(AuthorEntity authorEntity) {
        this.authorRepository.save(authorEntity);
    }
}
