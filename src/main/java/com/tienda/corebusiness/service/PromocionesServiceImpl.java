package com.tienda.corebusiness.service;

import com.tienda.corebusiness.model.Producto;
import com.tienda.corebusiness.model.Promociones;
import com.tienda.corebusiness.repository.PromocionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromocionesServiceImpl implements PromocionesService{

    @Autowired
    PromocionesRepository promocionesRepository;

    @Autowired
    ProductoService productoService;

    @Override
    public Promociones savePromocion(Promociones promocion) {
        Promociones p = new Promociones(promocion.getIdProducto(),promocion.getNombreProducto(),promocion.getPrecio(),promocion.getDescuento(),promocion.getEstado());
        p.setId(promocion.getId());

        Optional<Producto> producto = productoService.getProductoById(p.getIdProducto());
        producto.get().setPrecio(p.getPrecioDescuento());
        productoService.saveProducto(producto.get());

        return promocionesRepository.save(p);
    }

    @Override
    public Promociones savePromocionNueva(Promociones promocion) {

        List<Promociones> promociones = (List<Promociones>) promocionesRepository.findAll();
        for(Promociones p: promociones){
            if(p.getIdProducto() == promocion.getIdProducto()){
                return null;
            }
        }

        Promociones p = new Promociones(promocion.getIdProducto(),promocion.getNombreProducto(),promocion.getPrecio(),promocion.getDescuento(),promocion.getEstado());
        Optional<Producto> producto = productoService.getProductoById(p.getIdProducto());
        producto.get().setPrecio(p.getPrecioDescuento());
        productoService.saveProducto(producto.get());

        return promocionesRepository.save(p);
    }

    @Override
    public List<Promociones> getPromocionesByEstado(String estado) {
        return promocionesRepository.findByEstado(estado);
    }

    @Override
    public List<Promociones> getPromociones() {
        return (List<Promociones>) promocionesRepository.findAll();
    }

    @Override
    public Optional<Promociones> getPromocionByIdProducto(long id) {
        return promocionesRepository.findByIdProducto(id);
    }
}
