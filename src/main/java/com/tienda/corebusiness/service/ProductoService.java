package com.tienda.corebusiness.service;

import com.tienda.corebusiness.model.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Producto saveProducto(Producto producto);
    ArrayList<Producto> getAllProductos();

    List<Producto> getProductByLikeNombre(String nombre);

    Optional<Producto> getProductoById(long id);

    byte[] compressBytes(byte[] data);

    byte[] decompressBytes(byte[] data);

}
