package com.example.demo.Service.impl;

import com.example.demo.Model.Books;
import com.example.demo.Repository.impl.BookRepository;
import com.example.demo.Service.IbookService;

public class BookService implements IbookService {
    BookRepository bookRepository = new BookRepository();

    @Override
    public Books saveOrUpdateBook(Books book, Integer id) {
        return this.bookRepository.saveOrUpdateBook(book, id);
    }

}
