package Laboreo;

import Granos.TipoGrano;

import java.sql.Date;
import java.util.List;

/**
 * Created by OWNER on 9/4/2016.
 */
public class Laboreo {
    private String nombre;
    private Date lboFechaHoraInicio;
    private Date lboFechaHoraFin;
    private String lboDescripcion;
    private List detalles;
    private List lotes;
    private MomentoLaboreo momento;
    private TipoGrano tipoGrano;

    public Laboreo(Date lboFechaHoraInicio, Date lboFechaHoraFin, String lboDescripcion, List detalles, List lotes, MomentoLaboreo momento, TipoGrano tipoGrano) {
        this.setLboFechaHoraInicio(lboFechaHoraInicio);
        this.setLboFechaHoraFin(lboFechaHoraFin);
        this.setLboDescripcion(lboDescripcion);
        this.setDetalles(detalles);
        this.setLotes(lotes);
        this.setMomento(momento);
        this.setTipoGrano(tipoGrano);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getLboFechaHoraInicio() {
        return lboFechaHoraInicio;
    }

    public void setLboFechaHoraInicio(Date lboFechaHoraInicio) {
        this.lboFechaHoraInicio = lboFechaHoraInicio;
    }

    public Date getLboFechaHoraFin() {
        return lboFechaHoraFin;
    }

    public void setLboFechaHoraFin(Date lboFechaHoraFin) {
        this.lboFechaHoraFin = lboFechaHoraFin;
    }

    public String getLboDescripcion() {
        return lboDescripcion;
    }

    public void setLboDescripcion(String lboDescripcion) {
        this.lboDescripcion = lboDescripcion;
    }

    public List getDetalles() {
        return detalles;
    }

    public void setDetalles(List detalles) {
        this.detalles = detalles;
    }

    public List getLotes() {
        return lotes;
    }

    public void setLotes(List lotes) {
        this.lotes = lotes;
    }

    public MomentoLaboreo getMomento() {
        return momento;
    }


    public void setMomento(MomentoLaboreo momento) {
        this.momento = momento;
    }

    public TipoGrano getTipoGrano() {
        return tipoGrano;
    }

    public void setTipoGrano(TipoGrano tipoGrano) {
        this.tipoGrano = tipoGrano;
    }
}
