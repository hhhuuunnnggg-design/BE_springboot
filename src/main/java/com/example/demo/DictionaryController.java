package com.example.demo;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictionaryController {

    private final Map<String, String> dictionaryMap = Map.ofEntries(
            Map.entry("hello", "xin chào"),
            Map.entry("apple", "quả táo"));

    @GetMapping("/dictionary")
    public ResponseEntity<String> getTranslation(@RequestParam(defaultValue = "") String word) {
        // Lấy nghĩa từ từ điển và xử lý chữ hoa thành chữ thường
        String translate = dictionaryMap.get(word.trim().toLowerCase());
        // Nếu có nghĩa của từ
        if (translate != null) {
            return ResponseEntity.ok(translate);
        }
        // Trả về lỗi nếu không tìm thấy từ
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy dữ liệu trong từ điển");
    }

    // bài 3
    @GetMapping("/calculator")
    public ResponseEntity<String> getcalculator(@RequestParam(defaultValue = "") String number1,
            @RequestParam(defaultValue = "") String number2,
            @RequestParam(defaultValue = "") String thuchien) {

        if (number1.isEmpty()) {
            return ResponseEntity.badRequest().body("number 1 null");
        } else if (number2.isEmpty()) {
            return ResponseEntity.badRequest().body("number 2 null");
        } else if (thuchien.isEmpty()) {
            return ResponseEntity.badRequest().body("thuc hien null");
        }

        // chuyen string thanh number
        double firstnumber1 = Double.parseDouble(number1);
        double firstnumber2 = Double.parseDouble(number2);
        double result;

        return null;
    }

}
