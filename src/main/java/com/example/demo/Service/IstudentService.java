package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.Student;

public interface IstudentService {
    List<Student> findAll();

    Student findById(Integer id);

    Student save(Student student);

    Student updateStudentById(Integer id, Student student);

    void deleteStudentById(int id);

    List<Student> searchStudents(String name, String phone, String startDate, String endDate, String gender);
}
