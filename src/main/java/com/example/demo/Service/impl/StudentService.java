package com.example.demo.Service.impl;

import java.util.List;

import com.example.demo.exception.ApiException;
import com.example.demo.exception.ErrorCode;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Student;
import com.example.demo.Repository.impl.StudentRepository;
import com.example.demo.Service.IstudentService;

@Service
public class StudentService implements IstudentService {
    StudentRepository studentRepository = new StudentRepository();

    @Override
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return this.studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    public Student updateStudentById(Integer id, Student student) {
        return this.studentRepository.updateStudentById(id, student);
    }

    @Override
    public void deleteStudentById(int id) {
        this.studentRepository.deleteStudentById(id);
    }

    @Override
    public List<Student> searchStudents(String name, String phone, String startDate, String endDate, String gender) {
        return this.studentRepository.searchStudents(name, phone, startDate, endDate, gender);
    }

}
