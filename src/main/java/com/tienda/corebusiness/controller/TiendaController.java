package com.tienda.corebusiness.controller;

import com.tienda.corebusiness.model.AddCar;
import com.tienda.corebusiness.model.Banner;
import com.tienda.corebusiness.model.Categoria;
import com.tienda.corebusiness.model.Producto;
import com.tienda.corebusiness.service.AddCarService;
import com.tienda.corebusiness.service.BannerService;
import com.tienda.corebusiness.service.CategoriaService;
import com.tienda.corebusiness.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    AddCarService addCarService;

    @PostMapping("/saveCar")
    public AddCar saveCar(@RequestBody AddCar addCar){
        return addCarService.saveCar(addCar);
    }

    @GetMapping("/getCarByUser/{username}")
    public List<AddCar> getCarByUser(@PathVariable("username") String username){
        return addCarService.getCarByUsuario(username);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, String> deleteItemCar(@PathVariable("id") long id){
        Map<String, String> map = new HashMap<>();
        if(addCarService.deleteCar(id)) map.put("message", "Item eliminado");
        else map.put("message", "Item no eliminado");
        return map;
    }

    @DeleteMapping("/deleteAll")
    public Map<String, String> deleteCar(){
        Map<String, String> map = new HashMap<>();
        addCarService.deleteAllCar();
        map.put("message", "Carrito eliminado");
        return map;
    }



    @GetMapping("/getCategorias")
    public List<Categoria> getAllCategorias(){
        return categoriaService.getAllCategorias();
    }

    @GetMapping("/productos")
    public List<Producto> getAllProductos(){
        List<Producto> productosCompress = productoService.getAllProductos();
        List<Producto> productosDescompress = new ArrayList<>();
        for(Producto p: productosCompress){
            Producto producto = new Producto(p.getNombre(), p.getDescripcion(), p.getPrecio(),p.getStock(),p.getSrcImage(), productoService.decompressBytes(p.getPicByte()), p.getEstado(), p.getCategoria(), p.getProveedor());
            producto.setId(p.getId());
            productosDescompress.add(producto);
        }
        return productosDescompress;
    }

    @GetMapping("/producto/{id}")
    public Optional<Producto> getProductoById(@PathVariable("id") long id){
        final Optional<Producto> p = productoService.getProductoById(id);
        Producto producto = new Producto(p.get().getNombre(), p.get().getDescripcion(), p.get().getPrecio(),p.get().getStock(),p.get().getSrcImage(), productoService.decompressBytes(p.get().getPicByte()), p.get().getEstado(), p.get().getCategoria(), p.get().getProveedor());
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
