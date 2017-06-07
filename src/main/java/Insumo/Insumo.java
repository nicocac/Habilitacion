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
    private Long stock;
    private Long stockDisponible;

    public Insumo(String nombre, String observaciones, TipoInsumo tipoInsumo, String unidadMedida, Long stock, Long stockDisponible) {
        this.nombre = nombre;
        this.observaciones = observaciones;
        this.tipoInsumo = tipoInsumo;
        this.unidadMedida = unidadMedida;
        this.stock = stock;
        this.stockDisponible = stockDisponible;
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

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(Long stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public String toString(){
        return this.nombre+", "+this.observaciones;
    }

}
