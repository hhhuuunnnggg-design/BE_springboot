package com.example.demo.Model;

import java.time.LocalDate;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AutoConfiguration
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatBang {
    int id;
    String ten;
    String diachi;
    double dientich;
    String loaiMatbang;
    double giathue;
    LocalDate ngaythue;

}
