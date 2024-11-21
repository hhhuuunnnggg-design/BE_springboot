package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.MatBang;
import com.example.demo.Service.IMatBangService;
import com.example.demo.dto.ApiResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("matbang")
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatBangController {
    IMatBangService MatBangSerice;

    @GetMapping("/all")
    public List<MatBang> getAllStudents() {
        return MatBangSerice.findAll();
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<MatBang>>> getSearchMatBang(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "ten", required = false) String ten,
            @RequestParam(value = "diachi", required = false) String diachi,
            @RequestParam(value = "dientich", required = false) Double dientich,
            @RequestParam(value = "loaimatbang", required = false) String loaimatbang,
            @RequestParam(value = "giathue", required = false) Double giathue,
            @RequestParam(value = "startngaythue", required = false) String startngaythue) {

        List<MatBang> foundMatBangs = MatBangSerice.searchMatBang(
                id != null ? id : 0,
                ten,
                diachi,
                dientich,
                loaimatbang,
                giathue,
                startngaythue);

        ApiResponse<List<MatBang>> response = ApiResponse.<List<MatBang>>builder().data(foundMatBangs).build();
        return ResponseEntity.ok(response);
    }

}
