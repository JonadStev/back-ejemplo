package com.tienda.corebusiness.service;

import com.tienda.corebusiness.model.AddCar;

import java.util.List;
import java.util.Optional;

public interface AddCarService {

    AddCar saveCar(AddCar addCar);

    List<AddCar> getAllCar();

    Optional<AddCar> getCarById(long id);

    List<AddCar> getCarByUsuario(String usuario);

    boolean deleteCar(long id);
    void deleteAllCar();

}
