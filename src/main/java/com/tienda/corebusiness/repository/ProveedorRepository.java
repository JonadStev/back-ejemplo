package com.tienda.corebusiness.repository;

import com.tienda.corebusiness.model.Proveedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, Long> {

}
