package com.example.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.MatBang;
import com.example.demo.Repository.impl.MatBangRepository;
import com.example.demo.Service.IMatBangService;

@Service
public class MatBangSerice implements IMatBangService {
    @Autowired
    private MatBangRepository matBangRepository;

    @Override
    public List<MatBang> findAll() {
        return this.matBangRepository.findAll();
    }

    @Override
    public MatBang findById(int id) {
        return null;
    }

    @Override
    public List<MatBang> searchMatBang(int id, String ten, String diachi, Double dientich, String loaimatbang,
            Double giathue, String startngaythue) {
        return this.matBangRepository.searchMatBang(id, ten, diachi, dientich, loaimatbang, giathue, startngaythue);
    }

}
