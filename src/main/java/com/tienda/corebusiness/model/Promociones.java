package com.tienda.corebusiness.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_promociones")
public class Promociones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idProducto;
    private String nombreProducto;
    private double precio;
    private double descuento;
    private double precioDescuento;
    private String estado;

    public Promociones() {
    }

    public Promociones(long idProducto, String nombreProducto, double precio, double descuento, String estado) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.descuento = descuento;
        this.estado = estado;
        double des = (this.precio * this.descuento)/100;
        this.precioDescuento = precio - des;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecioDescuento() {
        double des = Math.round(precioDescuento*100.0)/100.0;
        return des;
    }

    public void setPrecioDescuento(double precioDescuento) {
        this.precioDescuento = precioDescuento;
    }
}
