package com.tienda.corebusiness.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_orden_compra")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idUsuario;
    private long idRepartidor;
    private String direccionEnvio;
    private String metodoPago;
    private String estadoEntrega;
    private Date fecha;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orden", cascade = CascadeType.ALL)
    private List<OrdenDetalle> ordenDetalle;

    public Orden() {
    }

    public Orden(long idUsuario, String direccionEnvio, String metodoPago, String estadoEntrega) {
        this.idUsuario = idUsuario;
        this.direccionEnvio = direccionEnvio;
        this.metodoPago = metodoPago;
        this.estadoEntrega = estadoEntrega;
        this.fecha = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(long idRepartidor) {
        this.idRepartidor = idRepartidor;
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

    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(String estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<OrdenDetalle> getOrdenDetalle() {
        return ordenDetalle;
    }

    public void setOrdenDetalle(List<OrdenDetalle> ordenDetalle) {
        this.ordenDetalle = ordenDetalle;
    }
}
