package com.tienda.corebusiness.controller;

import com.tienda.corebusiness.model.*;
import com.tienda.corebusiness.service.*;
import com.tienda.corebusiness.tiendaDto.CarritoDto;
import com.tienda.corebusiness.tiendaDto.OdenDto;
import com.tienda.corebusiness.tiendaDto.ProductosMasVendidosDto;
import com.tienda.security.model.Usuario;
import com.tienda.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    OrdenService ordenService;

    @PostMapping("/saveCar")
    public AddCar saveCar(@RequestBody AddCar addCar){
        return addCarService.saveCar(addCar);
    }

    @GetMapping("/getCarByUser/{username}")
    public List<AddCar> getCarByUser(@PathVariable("username") String username){
        return addCarService.getCarByUsuario(username);
    }

    @PostMapping("/saveOrden")
    public Map<String, String> saveOrden(@RequestBody OdenDto orden){

        Optional<Usuario> usuario = usuarioService.getByNombreUsuario(orden.getUsuario());
        Orden ordenCompra = new Orden(usuario.get().getId(), orden.getDireccionEnvio(), orden.getMetodoPago(), orden.getEstadoPedido());
        List<OrdenDetalle> ordenDetalle = new ArrayList<>();
        for (CarritoDto carrito: orden.getCarrito()) {
            ordenDetalle.add(new OrdenDetalle(ordenCompra, carrito.getIdProducto(), carrito.getUsuario(), carrito.getCantidad(), carrito.getPrecio()));
            Optional<AddCar> addcar = addCarService.getCarById(carrito.getId());
            addcar.get().setEstado("COMPRADO");
            addCarService.saveCar(addcar.get());
        }
        ordenCompra.setOrdenDetalle(ordenDetalle);
        long idCompra = ordenService.saveOrden(ordenCompra).getId();
        Map<String, String> map = new HashMap<>();
        map.put("ORDEN_COMPRA", String.valueOf(idCompra));
        return map;
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

    @GetMapping("/productoMasVendidos")
    public List<Producto> getProductoMasVendidos(){
        List<OrdenDetalle> detalleMasVendidos = ordenService.getAllDetalleOrden().stream().filter(x -> x.getCantidad() > 1).collect(Collectors.toList());
        List<Long> productosMasVendidosSinRepetir = detalleMasVendidos.stream().map(x -> x.getIdProducto()).distinct().collect(Collectors.toList());
        List<Producto> productosMasVendidos = new ArrayList<>();
        int contador = 0;
        for(long pmv: productosMasVendidosSinRepetir){
            if(contador == 10)
                break;
            System.out.println(pmv);
            Producto p = productoService.getProductoById(pmv).get();
            productosMasVendidos.add(new Producto(p.getNombre(), p.getDescripcion(), p.getPrecio(),p.getStock(),p.getSrcImage(), productoService.decompressBytes(p.getPicByte()), p.getEstado(), p.getCategoria(), p.getProveedor()));
            contador++;
        }
        return productosMasVendidos;
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
