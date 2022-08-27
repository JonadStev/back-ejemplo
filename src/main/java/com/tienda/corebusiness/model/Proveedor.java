package com.tienda.corebusiness.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    private String correo;

    private String telefono;

    private String estado;

    @OneToMany(mappedBy = "proveedor")
    private List<Producto> productos;

    public Proveedor(String nombre, String correo, String telefono, String estado) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Proveedor() {
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
