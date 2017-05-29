package Laboreo;

import Datos.LaboreoEntity;
import Datos.LoteEntity;
import Insumo.Insumo;
import Maquinaria.Maquinaria;

/**
 * Created by OWNER on 9/4/2016.
 */
public class DetalleDelLaboreo {

//    String[] columnNamesLaboreo = {"Clasificacion", "Nombre", "Descripcion", "Cantidad Lotes", "Lotes"};

    private LaboreoEntity laboreoEntity;
    private LoteEntity loteEntity;
    private String nombreLote;

    public DetalleDelLaboreo() {
    }

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

    public String getNombreLote() {
        return nombreLote;
    }

    public void setNombreLote(String nombreLote) {
        this.nombreLote = nombreLote;
    }
}
