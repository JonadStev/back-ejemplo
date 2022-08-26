package com.tienda.corebusiness.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_banner")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descripcion;

    @Column(name = "picByte", length = 1000)
    private byte[] picByte;

    public Banner(String descripcion, byte[] picByte) {
        this.descripcion = descripcion;
        this.picByte = picByte;
    }

    public Banner(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }
}
