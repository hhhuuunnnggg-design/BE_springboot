package com.example.demo.Repository;

import com.example.demo.Model.Books;
import com.example.demo.Model.Employee;

public interface IbookRepository {

    Books saveOrUpdateBook(Books book, Integer id);
}
