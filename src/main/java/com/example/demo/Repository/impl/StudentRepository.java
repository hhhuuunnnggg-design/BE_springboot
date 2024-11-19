package com.example.demo.Repository.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demo.Model.Employee;
import com.example.demo.Model.enums.Gender;
import com.example.demo.Repository.IstudentRepository;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.ErrorCode;

@Repository
public class StudentRepository implements IstudentRepository {

    private List<Employee> students = new ArrayList<>(Arrays.asList(
            new Employee(1, "Hùng", 10, "0213654215", LocalDate.parse("2000-01-01"), Gender.Female,
                    new BigDecimal("4500000.0")),
            new Employee(2, "hùng", 8, "0787495636", LocalDate.parse("1999-02-15"), Gender.Female,
                    new BigDecimal("7500000.0")),
            new Employee(3, "Huy", 10, "0213654219", LocalDate.parse("2001-06-20"), Gender.Male,
                    new BigDecimal("15000000.0")),
            new Employee(4, "Duy", 5, "0395648952", LocalDate.parse("2002-11-10"), Gender.Other,
                    new BigDecimal("9500000.0"))));

    // tìm theo id
    @Override
    public Employee findById(int id) {
        for (Employee student : students) {
            if (student.getId() == id) {
                System.out.println("Danh sách sinh viên hiện tại: " + students);
                System.out.println("Tìm kiếm id: " + id);
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Employee> searchStudents(String name, String phone, String startDate, String endDate, String gender,
            String tienluong) {
        LocalDate start = startDate.isEmpty() ? LocalDate.MIN : LocalDate.parse(startDate);
        LocalDate end = endDate.isEmpty() ? LocalDate.MAX : LocalDate.parse(endDate);

        return students.stream().filter(request -> request.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(request -> request.getNumberPhone().contains(phone))
                .filter(request -> gender.isEmpty() || request.getGender().name().equalsIgnoreCase(gender))
                .filter(request -> !request.getBirthDate().isBefore(start) && !request.getBirthDate().isAfter(end))
                .filter(request -> {
                    if (tienluong.isEmpty())
                        return true;
                    BigDecimal salary = request.getSalary();
                    switch (tienluong.toLowerCase()) {
                        case "lt5m":
                            return salary.compareTo(new BigDecimal("5000000")) < 0;
                        case "5-10":
                            return salary.compareTo(new BigDecimal("5000000")) >= 0
                                    && salary.compareTo(new BigDecimal("10000000")) < 0;
                        case "10-20":
                            return salary.compareTo(new BigDecimal("10000000")) >= 0
                                    && salary.compareTo(new BigDecimal("20000000")) <= 0;
                        case "gt20m":
                            return salary.compareTo(new BigDecimal("20000000")) > 0;
                        default:
                            return true;
                    }
                })
                .collect(Collectors.toList());
    }

    // duyệt tất cả
    @Override
    public List<Employee> findAll() {
        return students;
    }

    // thêm sv
    @Override
    public Employee save(Employee student) {
        student.setId((int) (Math.random() * 100000));
        students.add(student);
        return student;
    }

    // sua
    @Override
    public Employee updateStudentById(int id, Employee student) {
        Employee existingStudent = findById(id);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setCode(student.getCode());
            existingStudent.setNumberPhone(student.getNumberPhone());
            existingStudent.setBirthDate(student.getBirthDate());
            existingStudent.setGender(student.getGender());
            existingStudent.setSalary(student.getSalary());

            return existingStudent;
        } else {
            return null;
        }
    }

    @Override
    public void deleteStudentById(int id) {
        Employee student = findById(id);
        if (student != null) {
            students.remove(student);
        } else {
            throw new ApiException(ErrorCode.STUDENT_FIND_NOT);
        }
    }

}
