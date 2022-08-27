package com.tienda.corebusiness.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    private String descripcion;

    private double precio;

    private int stock;

    private String srcImage;

    @Column(name = "picByte", length = 1000)
    private byte[] picByte;

    private String estado;

    @OneToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @OneToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    public Producto(String nombre, String descripcion, double precio, int stock, String srcImage, byte[] picByte, String estado, Categoria categoria, Proveedor proveedor) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.srcImage = srcImage;
        this.picByte = picByte;
        this.estado = estado;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

    public Producto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(String srcImage) {
        this.srcImage = srcImage;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
