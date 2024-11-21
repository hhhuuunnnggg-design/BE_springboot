package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.MatBang;

public interface IMatBangService {
    List<MatBang> findAll();

    MatBang findById(int id);

    List<MatBang> searchMatBang(int id, String ten, String diachi, Double dientich, Integer loaimatbang, Double giathue,
            String startngaythue);

}