package com.tienda.corebusiness.service;

import com.tienda.corebusiness.model.Orden;
import com.tienda.corebusiness.model.OrdenDetalle;
import com.tienda.corebusiness.repository.OrdenDetalleRepository;
import com.tienda.corebusiness.repository.OrdenRepository;
import com.tienda.security.model.Usuario;
import com.tienda.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenServiceImpl implements OrdenService{

    @Autowired
    OrdenRepository ordenRepository;

    @Autowired
    OrdenDetalleRepository ordenDetalleRepository;

    @Autowired
    UsuarioService usuarioService;

    @Override
    public Orden saveOrden(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> getAllOrden() {
        List<Orden> ordenes = (List<Orden>) ordenRepository.findAll();
        return ordenes.stream().filter(x -> x.getIdRepartidor() == 0).collect(Collectors.toList());
    }

    @Override
    public Optional<Orden> getOrdenById(long id) {
        return ordenRepository.findById(id);
    }

    @Override
    public List<Orden> getOrdenByIdRepartidor(String nombreUsuario) {
        Optional<Usuario> repartidor = usuarioService.getByNombreUsuario(nombreUsuario);
        List<Orden> ordenes = ordenRepository.findByIdRepartidor(repartidor.get().getId());
        return ordenes.stream().filter(x -> x.getEstadoEntrega().equalsIgnoreCase("EN_PROCESO")).collect(Collectors.toList());
    }

    @Override
    public List<OrdenDetalle> getAllDetalleOrden() {
        return (List<OrdenDetalle>) ordenDetalleRepository.findAll();
    }

    @Override
    public List<OrdenDetalle> getDetalleByIdOrden(long id) {
        List<OrdenDetalle> detalles = (List<OrdenDetalle>) ordenDetalleRepository.findAll();
        return detalles.stream().filter(x -> x.getOrden() == id).collect(Collectors.toList());
    }

    @Override
    public boolean asignarRepartidor(String usuarioRepartidor, long idOrden) {
        try {
            Optional<Usuario> repartidor = usuarioService.getByNombreUsuario(usuarioRepartidor);
            Optional<Orden> orden = ordenRepository.findById(idOrden);
            orden.get().setIdRepartidor(repartidor.get().getId());
            orden.get().setEstadoEntrega("EN_PROCESO");
            ordenRepository.save(orden.get());
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean cerrarOrden(String usuarioRepartidor, long idOrden) {
        try {
            Optional<Usuario> repartidor = usuarioService.getByNombreUsuario(usuarioRepartidor);
            Optional<Orden> orden = ordenRepository.findById(idOrden);
            orden.get().setEstadoEntrega("ENTREGADO");
            ordenRepository.save(orden.get());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
