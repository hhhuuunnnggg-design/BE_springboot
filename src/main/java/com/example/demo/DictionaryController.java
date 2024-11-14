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
        String translate = dictionaryMap.get(word.trim().toLowerCase());
        if (translate != null) {
            return ResponseEntity.ok(translate);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy dữ liệu trong từ điển");
    }

}
