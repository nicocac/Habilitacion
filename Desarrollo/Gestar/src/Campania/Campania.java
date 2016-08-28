package Campania;

import java.sql.Date;
import java.util.List;
import Lote.Lote;

/**
 * Created by OWNER on 8/28/2016.
 */
public class Campania {

    private String denominacion;
    private Date fechaInicio;
    private Date fechaFinEstimada;
    private Date fechaFinReal;
    private List <Lote> lotes;

    public Campania() {
    }

    public Campania(String denominacion, Date fechaInicio, Date fechaFinEstimada, Date fechaFinReal, List<Lote> lotes) {
        this.denominacion = denominacion;
        this.fechaInicio = fechaInicio;
        this.fechaFinEstimada = fechaFinEstimada;
        this.fechaFinReal = fechaFinReal;
        this.lotes = lotes;
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinEstimada() {
        return fechaFinEstimada;
    }

    public void setFechaFinEstimada(Date fechaFinEstimada) {
        this.fechaFinEstimada = fechaFinEstimada;
    }

    public Date getFechaFinReal() {
        return fechaFinReal;
    }

    public void setFechaFinReal(Date fechaFinReal) {
        this.fechaFinReal = fechaFinReal;
    }

    public String toString(){
        return "Denominacion: "+denominacion+", Inicio: "+fechaInicio+", Fin Est: "+fechaFinEstimada+", Fin: "+fechaFinReal;
    }

}
