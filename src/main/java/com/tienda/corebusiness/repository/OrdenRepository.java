package com.tienda.corebusiness.repository;

import com.tienda.corebusiness.model.Orden;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends CrudRepository<Orden, Long> {
}
