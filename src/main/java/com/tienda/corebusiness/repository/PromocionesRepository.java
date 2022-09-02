package com.tienda.corebusiness.repository;

import com.tienda.corebusiness.model.OrdenDetalle;
import com.tienda.corebusiness.model.Promociones;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Repository
public interface PromocionesRepository extends CrudRepository<Promociones, Long> {

    ArrayList<Promociones> findByEstado(String estado);
    Optional<Promociones> findByIdProducto(long id);
}
