package com.tienda.corebusiness.service;

import com.tienda.corebusiness.model.Orden;
import com.tienda.corebusiness.model.OrdenDetalle;
import com.tienda.corebusiness.repository.OrdenDetalleRepository;
import com.tienda.corebusiness.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImpl implements OrdenService{

    @Autowired
    OrdenRepository ordenRepository;

    @Autowired
    OrdenDetalleRepository ordenDetalleRepository;

    @Override
    public Orden saveOrden(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> getAllOrden() {
        return (List<Orden>) ordenRepository.findAll();
    }

    @Override
    public Optional<Orden> getOrdenById(long id) {
        return ordenRepository.findById(id);
    }

    @Override
    public List<OrdenDetalle> getAllDetalleOrden() {
        return (List<OrdenDetalle>) ordenDetalleRepository.findAll();
    }
}
