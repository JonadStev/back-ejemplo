package com.tienda.corebusiness.repository;

import com.tienda.corebusiness.model.OrdenDetalle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface OrdenDetalleRepository extends CrudRepository<OrdenDetalle, Long> {

    ArrayList<OrdenDetalle> findByFechaBetween(Date des, Date has);
}
