package com.tienda.corebusiness.service;

import com.tienda.corebusiness.model.Proveedor;

import java.util.ArrayList;
import java.util.Optional;

public interface ProveedorService {

    Proveedor saveProveedor(Proveedor proveedor);

    ArrayList<Proveedor> getProveedores();
    ArrayList<Proveedor> getProveedoresAdmin();

    Optional<Proveedor> getProveedorById(long id);

}
