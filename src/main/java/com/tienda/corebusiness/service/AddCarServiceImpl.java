package com.tienda.corebusiness.service;

import com.tienda.corebusiness.model.AddCar;
import com.tienda.corebusiness.repository.AddCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddCarServiceImpl implements AddCarService{

    @Autowired
    AddCarRepository addCarRepository;

    @Override
    public AddCar saveCar(AddCar addCar) {
        return addCarRepository.save(addCar);
    }

    @Override
    public List<AddCar> getAllCar() {
        return (List<AddCar>) addCarRepository.findAll();
    }

    @Override
    public Optional<AddCar> getCarById(long id) {
        return addCarRepository.findById(id);
    }

    @Override
    public List<AddCar> getCarByUsuario(String usuario) {
        List<AddCar> lista = addCarRepository.findByUsuario(usuario);
        return lista.stream().filter(x -> (x.getEstado().equalsIgnoreCase("ABIERTO") || x.getEstado().equalsIgnoreCase("PENDIENTE") )).collect(Collectors.toList());
    }

    @Override
    public boolean deleteCar(long id) {
        try {
            Optional<AddCar> car = getCarById(id);
            addCarRepository.delete(car.get());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void deleteAllCar(String userName) {
        List<AddCar> lista = addCarRepository.findByUsuario(userName);
        lista.forEach(x -> {
            addCarRepository.delete(x);
        });
    }


}
