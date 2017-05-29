package Laboreo;

import Datos.LaboreoEntity;
import Datos.LoteEntity;
import Datos.TipoGranoEntity;

/**
 * Created by jagm on 05/03/2017.
 */
public class OrdenTrabajoLaboreo {

    private LaboreoEntity laboreoEntity;
    private LoteEntity loteEntity;
    private TipoGranoEntity semilla;

    public LaboreoEntity getLaboreoEntity() {
        return laboreoEntity;
    }

    public void setLaboreoEntity(LaboreoEntity laboreoEntity) {
        this.laboreoEntity = laboreoEntity;
    }

    public LoteEntity getLoteEntity() {
        return loteEntity;
    }

    public void setLoteEntity(LoteEntity loteEntity) {
        this.loteEntity = loteEntity;
    }

    public TipoGranoEntity getSemilla() {
        return semilla;
    }

    public void setSemilla(TipoGranoEntity semilla) {
        this.semilla = semilla;
    }


    @Override
    public String toString() {
        return
                laboreoEntity.getLboNombre();
    }
}
