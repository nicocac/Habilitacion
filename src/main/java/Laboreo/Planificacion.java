package Laboreo;

import Datos.CampaniaEntity;
import Datos.LaboreoEntity;
import Datos.LoteEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OWNER on 9/4/2016.
 */
public class Planificacion {

    private Integer planificacionId;

    private CampaniaEntity campania;

    private List<DetalleLote> listaDetallesLote;

    public Planificacion() {
    }

    public Integer getPlanificacionId() {
        return planificacionId;
    }

    public void setPlanificacionId(Integer planificacionId) {
        this.planificacionId = planificacionId;
    }

    public CampaniaEntity getCampania() {
        return campania;
    }

    public void setCampania(CampaniaEntity campania) {
        this.campania = campania;
    }

    public List<DetalleLote> getListaDetallesLote() {
        if(listaDetallesLote == null) listaDetallesLote = new ArrayList<>();
        return listaDetallesLote;
    }

    public void setListaDetallesLote(List<DetalleLote> listaDetallesLote) {
        this.listaDetallesLote = listaDetallesLote;
    }
}
