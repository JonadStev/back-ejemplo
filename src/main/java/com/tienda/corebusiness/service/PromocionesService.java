package com.tienda.corebusiness.service;

import com.tienda.corebusiness.model.Promociones;

import java.util.List;

public interface PromocionesService {

    Promociones savePromocion(Promociones promocion);

    Promociones savePromocionNueva(Promociones promocion);

    List<Promociones> getPromocionesByEstado(String estado);

    List<Promociones> getPromociones();

}
