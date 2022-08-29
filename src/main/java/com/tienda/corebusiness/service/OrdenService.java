package com.tienda.corebusiness.service;

import com.tienda.corebusiness.model.Orden;
import com.tienda.corebusiness.model.OrdenDetalle;

import java.util.List;
import java.util.Optional;

public interface OrdenService {

    Orden saveOrden(Orden orden);

    List<Orden> getAllOrden();

    Optional<Orden> getOrdenById(long id);
    List<Orden> getOrdenByIdRepartidor(String nombreUsuario);

    List<OrdenDetalle> getAllDetalleOrden();
    List<OrdenDetalle> getDetalleByIdOrden(long id);

    boolean asignarRepartidor(String usuarioRepartidor, long idOrden);

    boolean cerrarOrden(String usuarioRepartidor, long idOrden);

}

