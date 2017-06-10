package Laboreo;

import Insumo.Insumo;
import Maquinaria.Maquinaria;

/**
 * Created by OWNER on 9/4/2016.
 */
public class DetalleLaboreo {
    private String observaciones;
    private int cantidadIsumo;
    private int cantidadIsumoOriginal;
    private int cantidadMaquinariaOriginal;
    private int cantidadMaquinaria;
    private Insumo insumo;
    private Maquinaria maquinaria;


    public DetalleLaboreo() {
    }

    public DetalleLaboreo(String observaciones, int cantidadIsumo, int cantidadIsumoOriginal, int cantidadMaquinaria, Insumo insumo, Maquinaria maquinaria, int cantidadMaquinariaOriginal) {
        this.observaciones = observaciones;
        this.cantidadIsumo = cantidadIsumo;
        this.cantidadIsumoOriginal = cantidadIsumoOriginal;
        this.cantidadMaquinariaOriginal = cantidadMaquinariaOriginal;
        this.cantidadMaquinaria = cantidadMaquinaria;
        this.insumo = insumo;
        this.maquinaria = maquinaria;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getCantidadIsumo() {
        return cantidadIsumo;
    }

    public void setCantidadIsumo(int cantidadIsumo) {
        this.cantidadIsumo = cantidadIsumo;
    }

    public int getCantidadMaquinaria() {
        return cantidadMaquinaria;
    }

    public void setCantidadMaquinaria(int cantidadMaquinaria) {
        this.cantidadMaquinaria = cantidadMaquinaria;
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

    public int getCantidadIsumoOriginal() {
        return cantidadIsumoOriginal;
    }

    public void setCantidadIsumoOriginal(int cantidadIsumoOriginal) {
        this.cantidadIsumoOriginal = cantidadIsumoOriginal;
    }

    public int getCantidadMaquinariaOriginal() {
        return cantidadMaquinariaOriginal;
    }

    public void setCantidadMaquinariaOriginal(int cantidadMaquinariaOriginal) {
        this.cantidadMaquinariaOriginal = cantidadMaquinariaOriginal;
    }
}
