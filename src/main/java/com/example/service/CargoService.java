package com.example.service;

import com.example.entity.Cargo;

import java.util.List;

public interface CargoService {
    public List<Cargo> getAllCargo();
    public Cargo getCargo(Long id);

}
