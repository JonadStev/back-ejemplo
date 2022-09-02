package com.tienda.administrator.controller;

import com.google.gson.Gson;
import com.tienda.administrator.Reportes.ReportesVentasDto;
import com.tienda.corebusiness.model.*;
import com.tienda.corebusiness.service.*;
import com.tienda.security.model.Usuario;
import com.tienda.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdministradorController {

    @Autowired
    CategoriaService categoriaService;
    @Autowired
    ProductoService productoService;
    @Autowired
    DeliveryService deliveryService;

    @Autowired
    ProveedorService proveedorService;

    @Autowired
    BannerService bannerService;

    @Autowired
    OrdenService ordenService;

    @Autowired
    PromocionesService promocionesService;

    @Autowired
    UsuarioService usuarioService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/roles")
    public String getRoles(){
        return "Hola administrador";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updateUsuario")
    public Usuario updateUsuario(@RequestBody Usuario usuario){
        return usuarioService.udpateUser(usuario);
    }

    // ENDPOINT PARA LAS CATEGORIAS
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/saveCategoria")
    public Categoria saveCategoria(@RequestBody Categoria categoria){
        return categoriaService.saveCategoria(categoria);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getCategorias")
    public List<Categoria> getAllCategorias(){
        return categoriaService.getAllCategorias();
    }

    // ENDPOINT PARA LOS PRODUCTOS
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/saveProducto")
    public Producto saveProducto(@RequestParam("producto") String strProducto ,@RequestParam("fichero") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Gson gson = new Gson();
        Producto producto = gson.fromJson(strProducto, Producto.class);
        if(producto.getId() != 0) { //No es un producto nuevo
            if(!producto.getSrcImage().equalsIgnoreCase("SELECTED_IMG")){ //Buscar el producto por ID y setear la img
                Optional<Producto> p = productoService.getProductoById(producto.getId());
                producto.setPicByte(p.get().getPicByte());
            }else{
                producto.setPicByte(productoService.compressBytes(file.getBytes()));
            }
            Optional<Promociones> promo = promocionesService.getPromocionByIdProducto(producto.getId());
            if(!promo.isEmpty()){
                promo.get().setPrecio(producto.getPrecio());
                promocionesService.savePromocion(promo.get());
            }
        }else {
            producto.setPicByte(productoService.compressBytes(file.getBytes()));
        }
        return productoService.saveProducto(producto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/productos")
    public List<Producto> getAllProductos(){
        return productoService.getAllProductos();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/producto/{id}")
    public Optional<Producto> getProductoById(@PathVariable("id") long id){
        final Optional<Producto> p = productoService.getProductoById(id);
        Producto producto = new Producto(p.get().getNombre(), p.get().getDescripcion(), p.get().getPrecio(),p.get().getStock(),p.get().getSrcImage(), productoService.decompressBytes(p.get().getPicByte()), p.get().getEstado(), p.get().getCategoria(), p.get().getProveedor());
        producto.setId(p.get().getId());
        return Optional.of(producto);
    }

    //ENDPOINT PARA LOS DELIVERYS
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getDeliverys")
    public List<Usuario> getAllDelivery(){
        return deliveryService.getAllDelivery();
    }

    //ENDPOINT PARA LOS PROVEEDORES
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/saveProveedor")
    public Proveedor saveProveedor(@RequestBody Proveedor proveedor){
        return proveedorService.saveProveedor(proveedor);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/proveedores")
    public List<Proveedor> getProveedores(){
        return proveedorService.getProveedoresAdmin();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/proveedor/{id}")
    public Optional<Proveedor> getProveedor(@PathVariable("id") long id){
        return proveedorService.getProveedorById(id);
    }

    //ENDPOINT PARA BANNERS
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/saveBanner")
    public Banner saveBanner(@RequestParam("banner") String strBanner , @RequestParam("fichero") MultipartFile file) throws IOException {
        Gson gson = new Gson();
        Banner banner = gson.fromJson(strBanner, Banner.class);
        banner.setPicByte(bannerService.compressBytes(file.getBytes()));
        return bannerService.saveBanner(banner);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/banner/{id}")
    public Banner getBannerById(@PathVariable("id") long id){
        final Banner b = bannerService.getBannerById(id);
        Banner banner = new Banner(b.getDescripcion(),bannerService.decompressBytes(b.getPicByte()));
        banner.setId(b.getId());
        return banner;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/banner/delete/{id}")
    public Map<String, String> deleteBannerById(@PathVariable("id") long id){
        Map<String, String> map = new HashMap<>();
        map.put("message", "El banner ha sido elimiando");
        bannerService.deleteBannerById(id);
        return map;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/ordenesReportes")
    public List<Orden> getAllOrden(){
        return ordenService.getAllOrdenReportes();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/ventas")
    public List<ReportesVentasDto> getReporteVentas(){
        return ordenService.getReporteVentas();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/ventasPorFecha")
    public List<ReportesVentasDto> getReporteVentasByFecha(@RequestParam(name = "fechaDesde") String fechaDesde, @RequestParam(name = "fechaHasta") String fechaHasta){
        return ordenService.getReporteVentasByFecha(fechaDesde,fechaHasta);
    }

    //PROMOCIONES DESCUENTOS
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/savePromocion")
    public Promociones savePromocion(@RequestBody Promociones promocion){
        if(promocionesService.savePromocionNueva(promocion) == null)
            return null;
        return promocionesService.savePromocionNueva(promocion);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/updatePromocion")
    public Promociones udpatePromocion(@RequestBody Promociones promocion){
        return promocionesService.savePromocion(promocion);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/promociones")
    public List<Promociones> getPromociones(){
        return promocionesService.getPromociones();
    }


}
