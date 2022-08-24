package com.tienda.corebusiness.service;

import com.tienda.corebusiness.model.Producto;

import java.util.ArrayList;
import java.util.Optional;

public interface ProductoService {

    Producto saveProducto(Producto producto);
    ArrayList<Producto> getAllProductos();

    Optional<Producto> getProductoById(long id);

    byte[] compressBytes(byte[] data);

    byte[] decompressBytes(byte[] data);

}
