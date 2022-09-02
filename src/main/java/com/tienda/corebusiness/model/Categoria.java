package com.tienda.corebusiness.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombreCategoria;

    private String estado;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;


    public Categoria() {
    }

    public Categoria(String nombreCategoria, String estado) {
        this.nombreCategoria = nombreCategoria;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
