package com.example.demo.Repository;

import java.util.List;

import com.example.demo.Model.MatBang;

public interface IMatBangRepository {
    List<MatBang> findAll();

    MatBang findById(int id);

    List<MatBang> searchMatBang(int id, String ten, String diachi, Double dientich, Integer loaimatbang, Double giathue,
            String startngaythue);
}