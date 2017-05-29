package Laboreo;

import Datos.LaboreoEntity;
import Datos.LoteEntity;
import Insumo.Insumo;
import Maquinaria.Maquinaria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OWNER on 9/4/2016.
 */
public class DetalleLote {

    private Integer id;
    private LoteEntity loteEntity;
    private LaboreoEntity laboreoEntity;

    private List<DetalleLaboreos> listaDetalleLaboreos;

    public DetalleLote() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LoteEntity getLoteEntity() {
        return loteEntity;
    }

    public void setLoteEntity(LoteEntity loteEntity) {
        this.loteEntity = loteEntity;
    }

    public List<DetalleLaboreos> getListaDetalleLaboreos() {
        if(listaDetalleLaboreos == null) listaDetalleLaboreos = new ArrayList<>();

        return listaDetalleLaboreos;
    }

    public void setListaDetalleLaboreos(List<DetalleLaboreos> listaDetalleLaboreos) {
        this.listaDetalleLaboreos = listaDetalleLaboreos;
    }

    public LaboreoEntity getLaboreoEntity() {
        return laboreoEntity;
    }

    public void setLaboreoEntity(LaboreoEntity laboreoEntity) {
        this.laboreoEntity = laboreoEntity;
    }
}
