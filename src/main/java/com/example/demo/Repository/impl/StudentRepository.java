package com.example.demo.Repository.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demo.Model.Student;
import com.example.demo.Repository.IstudentRepository;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.ErrorCode;

@Repository
public class StudentRepository implements IstudentRepository {
    private List<Student> students = new ArrayList<>(
            Arrays.asList(new Student(1, "Hùng", 10, "0213654215", LocalDate.parse("2000-01-01"), "Nam"),
                    new Student(2, "hùng", 8, "0787495636", LocalDate.parse("1999-02-15"), "Nữ"),
                    new Student(3, "Huy", 10, "0213654219", LocalDate.parse("2001-06-20"), "Nam"),
                    new Student(4, "Duy", 5, "0395648952", LocalDate.parse("2002-11-10"), "Nữ")));

    @Override
    public List<Student> searchStudents(String name, String phone, String startDate, String endDate, String gender) {
        LocalDate start = startDate.isEmpty() ? LocalDate.MIN : LocalDate.parse(startDate);
        LocalDate end = endDate.isEmpty() ? LocalDate.MAX : LocalDate.parse(endDate);

        return students.stream()
                .filter(st -> st.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(st -> st.getNumberPhone().contains(phone))
                .filter(st -> st.getGender().toLowerCase().equals(gender.toLowerCase()))
                .filter(st -> !st.getBirthDate().isBefore(start) && !st.getBirthDate().isAfter(end))
                .collect(Collectors.toList());
    }

    // duyệt tất cả
    @Override
    public List<Student> findAll() {
        return students;
    }

    // tìm theo id
    @Override
    public Student findById(Integer id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // thêm sv
    @Override
    public Student save(Student student) {
        student.setId((int) (Math.random() * 100000));
        students.add(student);
        return null;
    }

    // sua
    @Override
    public Student updateStudentById(int id, Student student) {
        Student existingStudent = findById(id);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setCode(student.getCode());
            existingStudent.setNumberPhone(student.getNumberPhone());
            existingStudent.setBirthDate(student.getBirthDate());
            return existingStudent;
        } else {
            return null;
        }
    }

    @Override
    public void deleteStudentById(int id) {
        Student student = findById(id);
        if (student != null) {
            students.remove(student);
        } else {
            throw new ApiException(ErrorCode.STUDENT_FIND_NOT);
        }
    }

}
