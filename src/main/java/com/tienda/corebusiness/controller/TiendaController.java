package com.tienda.corebusiness.controller;

import com.tienda.corebusiness.model.Banner;
import com.tienda.corebusiness.model.Categoria;
import com.tienda.corebusiness.model.Producto;
import com.tienda.corebusiness.service.BannerService;
import com.tienda.corebusiness.service.CategoriaService;
import com.tienda.corebusiness.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tienda")
@CrossOrigin
public class TiendaController {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    ProductoService productoService;

    @Autowired
    BannerService bannerService;

    @GetMapping("/getCategorias")
    public List<Categoria> getAllCategorias(){
        return categoriaService.getAllCategorias();
    }

    @GetMapping("/productos")
    public List<Producto> getAllProductos(){
        List<Producto> productosCompress = productoService.getAllProductos();
        List<Producto> productosDescompress = new ArrayList<>();
        for(Producto p: productosCompress){
            Producto producto = new Producto(p.getNombre(), p.getPrecio(),p.getStock(),p.getSrcImage(), productoService.decompressBytes(p.getPicByte()), p.getEstado(), p.getCategoria());
            producto.setId(p.getId());
            productosDescompress.add(producto);
        }
        return productosDescompress;
    }

    @GetMapping("/producto/{id}")
    public Optional<Producto> getProductoById(@PathVariable("id") long id){
        final Optional<Producto> p = productoService.getProductoById(id);
        Producto producto = new Producto(p.get().getNombre(), p.get().getPrecio(),p.get().getStock(),p.get().getSrcImage(), productoService.decompressBytes(p.get().getPicByte()), p.get().getEstado(), p.get().getCategoria());
        producto.setId(p.get().getId());
        return Optional.of(producto);
    }

    @GetMapping("/banners")
    public List<Banner> getBanners(){
        List<Banner> bannerCompress = bannerService.getBanners();
        List<Banner> bannerDescompress = new ArrayList<>();
        for(Banner b: bannerCompress){
            Banner bann = new Banner(b.getDescripcion(),bannerService.decompressBytes(b.getPicByte()));
            bann.setId(b.getId());
            bannerDescompress.add(bann);
        }
        return bannerDescompress;
    }
}
