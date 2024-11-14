package com.example.demo.crud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class studentController {
    private List<student> students = new ArrayList<>(
            Arrays.asList(
                    new student(1, "hùng", 10),
                    new student(2, "huy", 10),
                    new student(3, "duy", 5)));

    @GetMapping("/students")
    public List<student> gStudents() {
        return students;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Object> getstudentByid(@PathVariable long id) {
        for (student st : students) {
            if (st.getId() == id) {
                return ResponseEntity.status(HttpStatus.OK).body(st);
            }
        }
        // Trả về thông báo lỗi nếu không tìm thấy sinh viên với ID
        String errorMessage = "Không tìm thấy sinh viên với ID: " + id;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
    

}
