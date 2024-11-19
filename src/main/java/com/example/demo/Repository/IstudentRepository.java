package com.example.demo.Repository;

import java.util.List;

import com.example.demo.Model.Employee;

public interface IstudentRepository {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee student);

    Employee updateStudentById(int id, Employee student);

    void deleteStudentById(int id);

    List<Employee> searchStudents(String name, String phone, String startDate, String endDate, String gender,
            String tienluong);
}
