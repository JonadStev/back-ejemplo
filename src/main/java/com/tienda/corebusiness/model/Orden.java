package com.tienda.corebusiness.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_orden_compra")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idUsuario;
    private String nombreCliente;
    private long idRepartidor;
    private String direccionEnvio;
    private String metodoPago;
    private String estadoEntrega;
    private Date fecha;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orden", cascade = CascadeType.ALL)
    private List<OrdenDetalle> ordenDetalle;

    public Orden() {
    }

    public Orden(long idUsuario, String nombreCliente, String direccionEnvio, String metodoPago, String estadoEntrega) {
        this.idUsuario = idUsuario;
        this.nombreCliente = nombreCliente;
        this.direccionEnvio = direccionEnvio;
        this.metodoPago = metodoPago;
        this.estadoEntrega = estadoEntrega;

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern);
        String dateFormatString = simpleDateFormat.format(new Date());
        Date dateF = null;
        try {
            dateF = simpleDateFormat.parse(dateFormatString);
        }catch (Exception e){}
        this.fecha = dateF;
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

    public String getFecha() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(fecha);
        return strDate;
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
