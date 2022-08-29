package com.tienda.corebusiness.repository;

import com.tienda.corebusiness.model.OrdenDetalle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDetalleRepository extends CrudRepository<OrdenDetalle, Long> {
}
