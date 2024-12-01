package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Books;
import com.example.demo.Model.Employee;
import com.example.demo.Repository.IbookRepository;
import com.example.demo.Service.IbookService;
import com.example.demo.dto.ApiResponse;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.ErrorCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("book")
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BooksController {
    IbookService ibookService;

    // thêm hoặc cập nhật
    @PostMapping("")
    public ResponseEntity<ApiResponse<Books>> saveOrUpdate(
            @RequestBody Books book,
            @RequestParam(value = "id", required = false) Integer id) {

        Books savedBook = ibookService.saveOrUpdateBook(book, id);
        if (savedBook == null) {
            throw new ApiException(ErrorCode.STUDENT_FIND_NOT);
        }
        if (id == null) {
            ApiResponse<Books> response = new ApiResponse<>(200, "them Book mới thành công", savedBook);
            return ResponseEntity.ok(response);
        }
        ApiResponse<Books> response = new ApiResponse<>(200, "đã cập nhật thành công", savedBook);
        return ResponseEntity.ok(response);
    }

}
