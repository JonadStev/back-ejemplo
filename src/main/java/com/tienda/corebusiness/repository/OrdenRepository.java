package com.tienda.corebusiness.repository;

import com.tienda.corebusiness.model.AddCar;
import com.tienda.corebusiness.model.Orden;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends CrudRepository<Orden, Long> {

    List<Orden> findByIdRepartidor(long id);
}
