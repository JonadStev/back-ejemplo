package com.tienda.corebusiness.repository;

import com.tienda.corebusiness.model.AddCar;
import com.tienda.security.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddCarRepository extends CrudRepository<AddCar, Long> {

    List<AddCar> findByUsuario(String nombreUsuario);
}
