package Insumo;

import TipoInsumo.TipoInsumo;

/**
 * Created by OWNER on 5/29/2016.
 */
public class Insumo {
    private String nombre;
    private String observaciones;
    private TipoInsumo tipoInsumo;
    private String unidadMedida;
    private double stock;

    public Insumo(String nombre, String observaciones, TipoInsumo tipoInsumo, String unidadMedida) {
        this.nombre = nombre;
        this.observaciones = observaciones;
        this.tipoInsumo = tipoInsumo;
        this.unidadMedida = unidadMedida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoInsumo getTipoInsumo() {
        return tipoInsumo;
    }

    public void setTipoInsumo(TipoInsumo tipoInsumo) {
        this.tipoInsumo = tipoInsumo;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String toString(){
        return this.nombre+", "+this.observaciones;
    }

}
