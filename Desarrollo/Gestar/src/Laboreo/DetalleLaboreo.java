package Laboreo;

import Insumo.Insumo;
import Maquinaria.Maquinaria;

/**
 * Created by OWNER on 9/4/2016.
 */
public class DetalleLaboreo {
    private String observaciones;
    private int cantidad;
    private Insumo insumo;
    private Maquinaria maquinaria;

    public DetalleLaboreo(String observaciones, int cantidad, Insumo insumo, Maquinaria maquinaria) {
        this.setObservaciones(observaciones);
        this.setCantidad(cantidad);
        this.setInsumo(insumo);
        this.setMaquinaria(maquinaria);
    }


    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Maquinaria getMaquinaria() {
        return maquinaria;
    }

    public void setMaquinaria(Maquinaria maquinaria) {
        this.maquinaria = maquinaria;
    }
}
