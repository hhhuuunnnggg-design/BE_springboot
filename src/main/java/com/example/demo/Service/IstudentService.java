package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.Employee;

public interface IstudentService {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee student);

    Employee updateStudentById(Integer id, Employee student);

    void deleteStudentById(int id);

    List<Employee> searchStudents(String name, String phone, String startDate, String endDate, String gender,
            String tienluong);

    Employee saveOrUpdate(Employee employee, Integer id);
}
