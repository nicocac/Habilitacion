package Repository;

import Conexion.Coneccion;
import Datos.LaboreoEntity;
import Datos.PlanificacionCampaniaEntity;
import Datos.TipoInsumoEntity;
import Datos.TipoLaboreoEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class PlanificacionRepository {
    Session session = Coneccion.getSession();

    public List<TipoInsumoEntity> getAllTipoInsumos(){
        List<TipoInsumoEntity> listaTipoInsumo = new ArrayList<>();
         session = Coneccion.getSession();
        TipoInsumoEntity tipoInsumo;
        Query query = session.createQuery("select x from TipoInsumoEntity x");
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoInsumo = (TipoInsumoEntity) iter.next();
            listaTipoInsumo.add(tipoInsumo);
        }
        session.close();
        return listaTipoInsumo;
    }


    public TipoLaboreoEntity getTipoLaboreoByNombre(String nombre){
         session = Coneccion.getSession();
        TipoLaboreoEntity tipoLaboreo = new TipoLaboreoEntity();
        Query query = session.createQuery("select x from TipoLaboreoEntity x where ucase(tpoNombre) like ucase(:pNombre) and tpoFechaBaja is null");
        query.setParameter("pNombre", nombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoLaboreo = (TipoLaboreoEntity) iter.next();
        }
        session.close();
        return tipoLaboreo;
    }


    public PlanificacionCampaniaEntity getPlanificadaById(Integer id){
        session = Coneccion.getSession();
        List<PlanificacionCampaniaEntity> listaPlanificacion = new ArrayList<>();
        PlanificacionCampaniaEntity planificacionCampania = new PlanificacionCampaniaEntity();
        Query query = session.createQuery("select x from PlanificacionCampaniaEntity x " +
                "where ucase(x.id) like ucase(:pId) and fechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            planificacionCampania = (PlanificacionCampaniaEntity) iter.next();
            listaPlanificacion.add(planificacionCampania);
        }
        session.close();
        return planificacionCampania;
    }

    public List<LaboreoEntity> getLaboreosByCampIdPlanificadaId(Integer id){
        session = Coneccion.getSession();
        List<LaboreoEntity> listaLaboreoEntity = new ArrayList<>();
        LaboreoEntity laboreo = new LaboreoEntity();
        Query query = session.createQuery("select x.laboreo from DetallePlanificacionCampaniaLaboreosEntity x " +
                "where ucase(x.planificacion.id) like ucase(:pId) and x.fechaBaja is null and x.tieneOrdenTrabajo = false");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            laboreo = (LaboreoEntity) iter.next();
            listaLaboreoEntity.add(laboreo);
        }
        session.close();
        return listaLaboreoEntity;
    }

}
