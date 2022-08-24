package com.tienda.administrator.controller;

import com.google.gson.Gson;
import com.tienda.corebusiness.model.Categoria;
import com.tienda.corebusiness.model.Producto;
import com.tienda.corebusiness.model.Proveedor;
import com.tienda.corebusiness.service.CategoriaService;
import com.tienda.corebusiness.service.DeliveryService;
import com.tienda.corebusiness.service.ProductoService;
import com.tienda.corebusiness.service.ProveedorService;
import com.tienda.security.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/saludar")
    public String getMessageAdmin(){
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
        Producto producto = new Producto(p.get().getNombre(), p.get().getPrecio(),p.get().getStock(),p.get().getSrcImage(), productoService.decompressBytes(p.get().getPicByte()), p.get().getEstado(), p.get().getCategoria());
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
        return proveedorService.getProveedores();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/proveedor/{id}")
    public Optional<Proveedor> getProveedor(@PathVariable("id") long id){
        return proveedorService.getProveedorById(id);
    }
}
