package Laboreo;

import Datos.LaboreoEntity;
import Datos.TipoGranoEntity;
import Insumo.Insumo;
import Maquinaria.Maquinaria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OWNER on 9/4/2016.
 */
public class DetalleLaboreos {

    private Integer id;

    private LaboreoEntity laboreoEntity;
    private TipoGranoEntity tipoGranoEntity;

    private List<DetalleLaboreo> listaDetalleLaboreo;


    public DetalleLaboreos() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LaboreoEntity getLaboreoEntity() {
        return laboreoEntity;
    }

    public void setLaboreoEntity(LaboreoEntity laboreoEntity) {
        this.laboreoEntity = laboreoEntity;
    }

    public List<DetalleLaboreo> getListaDetalleLaboreo() {
        if(listaDetalleLaboreo == null) listaDetalleLaboreo = new ArrayList<>();

        return listaDetalleLaboreo;
    }

    public void setListaDetalleLaboreo(List<DetalleLaboreo> listaDetalleLaboreo) {
        this.listaDetalleLaboreo = listaDetalleLaboreo;
    }

    public TipoGranoEntity getTipoGranoEntity() {
        return tipoGranoEntity;
    }

    public void setTipoGranoEntity(TipoGranoEntity tipoGranoEntity) {
        this.tipoGranoEntity = tipoGranoEntity;
    }
}
