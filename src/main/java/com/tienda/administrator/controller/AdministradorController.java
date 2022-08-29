package com.tienda.administrator.controller;

import com.google.gson.Gson;
import com.tienda.corebusiness.model.Banner;
import com.tienda.corebusiness.model.Categoria;
import com.tienda.corebusiness.model.Producto;
import com.tienda.corebusiness.model.Proveedor;
import com.tienda.corebusiness.service.*;
import com.tienda.security.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/roles")
    public String getRoles(){
        return "Hola administrador";
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
        String uploadDir = "img/productos";
        Gson gson = new Gson();
        Producto producto = gson.fromJson(strProducto, Producto.class);
        producto.setSrcImage(uploadDir+"/"+fileName);
        producto.setPicByte(productoService.compressBytes(file.getBytes()));
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
}
