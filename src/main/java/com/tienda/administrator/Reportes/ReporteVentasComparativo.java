package com.tienda.administrator.Reportes;

public class ReporteVentasComparativo {

    private int anio;
    private double [] totalVentas;

    public ReporteVentasComparativo() {
    }

    public ReporteVentasComparativo(int anio, double[] totalVentas) {
        this.anio = anio;
        this.totalVentas = totalVentas;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double[] getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(double[] totalVentas) {
        this.totalVentas = totalVentas;
    }
}
