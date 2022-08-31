package com.tienda.corebusiness.tiendaDto;

public class DecuentoDto {

    private long idProducto;
    private String nombreProducto;
    private double precio;
    private double descuento;
    private String estado;

    public DecuentoDto() {
    }

    public DecuentoDto(long idProducto, String nombreProducto, double precio, double descuento, String estado) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.descuento = descuento;
        this.estado = estado;
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
}
