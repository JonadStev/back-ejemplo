package com.tienda.administrator.controller;

import com.google.gson.Gson;
import com.tienda.corebusiness.model.Categoria;
import com.tienda.corebusiness.model.Producto;
import com.tienda.corebusiness.service.CategoriaService;
import com.tienda.corebusiness.service.DeliveryService;
import com.tienda.corebusiness.service.ProductoService;
import com.tienda.security.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
        return productoService.saveProducto(producto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getProductos")
    public List<Producto> getAllProductos(){
        return productoService.getAllProductos();
    }

    //ENDPOINT PARA LOS DELIVERYS
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getDeliverys")
    public List<Usuario> getAllDelivery(){
        return deliveryService.getAllDelivery();
    }

}
