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
    private Integer secuencia;

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

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    @Override
    public String toString() {
        return
                "Secuencia: "  +this.getSecuencia() + " LABOREO: "+laboreoEntity.getLboNombre() + " --- LOTE: " + loteEntity.getLteDenominacion()+ " --- SEMILLA: "+semilla.getTgrNombre();
    }
}
