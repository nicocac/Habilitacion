package Lote;

import java.sql.Date;

/**
 * Created by OWNER on 8/28/2016.
 */
public class Lote {
    private String denominacion;
    private int cantMetros;
    private String ubicacion;
    private Date fechaDesde;
    private Date fechaHasta;

    public Lote() {
    }

    public Lote(String denominacion, int cantMetros, String ubicacion, Date fechaDesde, Date fechaHasta) {
        this.setDenominacion(denominacion);
        this.setCantMetros(cantMetros);
        this.setUbicacion(ubicacion);
        this.setFechaDesde(fechaDesde);
        this.setFechaHasta(fechaHasta);
    }


    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public int getCantMetros() {
        return cantMetros;
    }

    public void setCantMetros(int cantMetros) {
        this.cantMetros = cantMetros;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String toString(){
        return this.getDenominacion()+", Ubic: "+this.ubicacion+", Cant. Mts: "+this.cantMetros+", Desde: "+this.fechaDesde+", Hasta: "+this.fechaHasta;
    }
}
