package com.tienda.corebusiness.service;

import com.tienda.corebusiness.model.Orden;
import com.tienda.corebusiness.model.OrdenDetalle;

import java.util.List;
import java.util.Optional;

public interface OrdenService {

    Orden saveOrden(Orden orden);

    List<Orden> getAllOrden();

    Optional<Orden> getOrdenById(long id);

    List<OrdenDetalle> getAllDetalleOrden();

}
