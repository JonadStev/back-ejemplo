package com.tienda.corebusiness.tiendaDto;

import java.util.List;

public class OdenDto {

    private String direccionEnvio;
    private String metodoPago;
    private String estadoPedido;
    private String usuario;
    private List<CarritoDto> carrito;

    public OdenDto() {
    }

    public OdenDto(String direccionEnvio, String metodoPago, String estadoPedido, String usuario, List<CarritoDto> carrito) {
        this.direccionEnvio = direccionEnvio;
        this.metodoPago = metodoPago;
        this.estadoPedido = estadoPedido;
        this.usuario = usuario;
        this.carrito = carrito;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<CarritoDto> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<CarritoDto> carrito) {
        this.carrito = carrito;
    }
}
