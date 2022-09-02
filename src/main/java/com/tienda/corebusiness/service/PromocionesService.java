package com.tienda.corebusiness.service;

import com.tienda.corebusiness.model.Promociones;

import java.util.List;
import java.util.Optional;

public interface PromocionesService {

    Promociones savePromocion(Promociones promocion);

    Promociones savePromocionNueva(Promociones promocion);

    List<Promociones> getPromocionesByEstado(String estado);

    List<Promociones> getPromociones();

    Optional<Promociones> getPromocionByIdProducto(long id);

}
