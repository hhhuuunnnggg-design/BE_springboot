package com.example.demo.crud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/students")
@RestController
public class studentController {
    private List<Students> students = new ArrayList<>(
            Arrays.asList(
                    new Students(1, "hùng", 10),
                    new Students(2, "huy", 10),
                    new Students(3, "duy", 5)));

    // view
    @GetMapping("")
    public List<Students> getStudents() {
        return students;
    }

    // tìm kiếm theo id
    @GetMapping("/search/{id}")
    public ResponseEntity<Object> getSreachStudentById(@PathVariable int id) {
        for (Students st : students) {
            if (st.getId() == id) {
                return ResponseEntity.status(HttpStatus.OK).body(st);
            }
        }
        String errorMessage = "Không tìm thấy sinh viên với ID: " + id;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    // thêm sinh viên
    @PostMapping("/create")
    public ResponseEntity<Students> create(@RequestBody Students student) {
        student.setId((int)Math.random());
        students.add(student);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    // xóa sinh viên theo id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable int id) {
        return students.stream().filter(e -> e.getId() == id).findFirst()
                .map(s -> {
                    students.remove(s);
                        return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã này không tồn tại để xóa"));
    }

    // sửa
    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateStudentById(@PathVariable Long id, @RequestBody Students student) {
        return students.stream().filter(e -> e.getId() == id).findFirst()
                .map(e -> {
                    e.setName(student.getName());
                    e.setCode(student.getCode());
                    return ResponseEntity.status(HttpStatus.OK).body("cập nhật thành công");
                }).orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cập nhật không thành công"));
    }
}
