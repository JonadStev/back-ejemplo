package com.tienda.corebusiness.tiendaDto;

public class AsignarRepartidorDto {

    private String usuarioRepartidor;
    private long idOrden;

    public AsignarRepartidorDto() {
    }

    public AsignarRepartidorDto(String usuarioRepartidor, long idOrden) {
        this.usuarioRepartidor = usuarioRepartidor;
        this.idOrden = idOrden;
    }

    public String getUsuarioRepartidor() {
        return usuarioRepartidor;
    }

    public void setUsuarioRepartidor(String usuarioRepartidor) {
        this.usuarioRepartidor = usuarioRepartidor;
    }

    public long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(long idOrden) {
        this.idOrden = idOrden;
    }
}
