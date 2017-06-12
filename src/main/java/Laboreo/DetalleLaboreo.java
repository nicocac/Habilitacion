package Laboreo;

import Insumo.Insumo;
import Maquinaria.Maquinaria;

/**
 * Created by OWNER on 9/4/2016.
 */
public class DetalleLaboreo {
    private String observaciones;
    private int cantidadIsumo;
    private int cantidadInsumoTotal;
    private int cantidadIsumoOriginal;
    private int cantidadMaquinariaOriginal;
    private int cantidadMaquinariaTotal;
    private int cantidadHorasMaquinariaTotal;
    private int cantidadMaquinaria;
    private Insumo insumo;
    private Maquinaria maquinaria;


    public DetalleLaboreo() {
    }

    public int getCantidadHorasMaquinariaTotal() {
        return cantidadHorasMaquinariaTotal;
    }

    public void setCantidadHorasMaquinariaTotal(int cantidadHorasMaquinariaTotal) {
        this.cantidadHorasMaquinariaTotal = cantidadHorasMaquinariaTotal;
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

    public int getCantidadInsumoTotal() {
        return cantidadInsumoTotal;
    }

    public void setCantidadInsumoTotal(int cantidadInsumoTotal) {
        this.cantidadInsumoTotal = cantidadInsumoTotal;
    }

    public int getCantidadMaquinariaTotal() {
        return cantidadMaquinariaTotal;
    }

    public void setCantidadMaquinariaTotal(int cantidadMaquinariaTotal) {
        this.cantidadMaquinariaTotal = cantidadMaquinariaTotal;
    }
}
