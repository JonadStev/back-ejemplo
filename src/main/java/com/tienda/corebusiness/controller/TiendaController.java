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
        return productoService.getAllProductos();
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
