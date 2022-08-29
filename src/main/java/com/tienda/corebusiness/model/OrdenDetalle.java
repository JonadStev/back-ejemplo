package com.tienda.corebusiness.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "tbl_detalle_orden")
public class OrdenDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden")
    private Orden orden;
    private long idProducto;
    private String usuario;
    private int cantidad;
    private double precio;
    private double subtotal;
    private double iva;
    private double total;
    private Date fecha;

    public OrdenDetalle() {
    }

    public OrdenDetalle(Orden orden, long idProducto, String usuario, int cantidad, double precio) {
        this.orden = orden;
        this.idProducto = idProducto;
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = (this.cantidad * this.precio)/1.12;
        this.iva = (this.subtotal*0.12);
        this.total = this.cantidad * this.precio;
        this.fecha = new Date();
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(fecha);
        return strDate;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public long getOrden() {
        return orden.getId();
    }
}
