package com.tienda.corebusiness.service;

import com.tienda.Mail.EnvioEmail;
import com.tienda.corebusiness.model.Proveedor;
import com.tienda.corebusiness.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    ProveedorRepository proveedorRepository;

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public ArrayList<Proveedor> getProveedores() {
        ArrayList<Proveedor> lista = (ArrayList<Proveedor>) proveedorRepository.findAll();
        return (ArrayList<Proveedor>) lista.stream().filter(x -> x.getEstado().equalsIgnoreCase("ACTIVO")).collect(Collectors.toList());
    }

    @Override
    public Optional<Proveedor> getProveedorById(long id) {
        //envioEmail.sendEmail("jona.steven001@gmail.com", "PRUEBA", "ASDFASDFADFA");
        return proveedorRepository.findById(id);
    }
}
