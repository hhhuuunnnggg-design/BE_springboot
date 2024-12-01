package com.example.demo.Service;

import com.example.demo.Model.Books;

public interface IbookService {

    Books saveOrUpdateBook(Books book, Integer id);
}
