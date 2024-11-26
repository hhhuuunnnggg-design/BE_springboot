package com.example.demo.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Model.Employee;
import com.example.demo.Repository.impl.StudentRepository;
import com.example.demo.Service.IstudentService;

@Service
public class StudentService implements IstudentService {
    StudentRepository studentRepository = new StudentRepository();

    @Override
    public List<Employee> findAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return this.studentRepository.findById(id);
    }

    @Override
    public Employee save(Employee student) {
        return this.studentRepository.save(student);
    }

    @Override
    public Employee updateStudentById(Integer id, Employee student) {
        return this.studentRepository.updateStudentById(id, student);
    }

    @Override
    public void deleteStudentById(int id) {
        this.studentRepository.deleteStudentById(id);
    }

    @Override
    public List<Employee> searchStudents(String name, String phone, String startDate, String endDate, String gender,
            String tienluong) {
        return this.studentRepository.searchStudents(name, phone, startDate, endDate, gender, tienluong);
    }

    @Override
    public Employee saveOrUpdate(Employee employee, Integer id) {
        return this.studentRepository.saveOrUpdate(employee, id);
    }

}
