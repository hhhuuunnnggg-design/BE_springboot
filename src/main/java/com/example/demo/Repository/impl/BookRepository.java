package com.example.demo.Repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.demo.Model.Books;
import com.example.demo.Repository.IbookRepository;

public class BookRepository implements IbookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Books saveOrUpdateBook(Books book, Integer id) {
        if (id != null) {
            book.setBookId(id); // set ID if provided
            return entityManager.merge(book); // Merge will update existing book if found
        } else {
            entityManager.persist(book); // Persist if new book
            return book; // Return the newly persisted book
        }
    }

}
