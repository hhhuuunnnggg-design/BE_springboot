package com.example.demo.Repository;

import java.util.List;

import com.example.demo.Model.Student;

public interface IstudentRepository {
    List<Student> findAll();

    Student findById(Integer id);

    Student save(Student student);

    Student updateStudentById(int id, Student student);

    void deleteStudentById(int id);

    List<Student> searchStudents(String name, String phone, String startDate, String endDate, String gender);
}
