package Repository;

import Conexion.Conexion;
import Datos.*;
import Laboreo.OrdenTrabajoLaboreo;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class PlanificacionRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<TipoInsumoEntity> getAllTipoInsumos(){
        List<TipoInsumoEntity> listaTipoInsumo = new ArrayList<>();
        Session session = Conexion.getSessionFactory().openSession();
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
        Session session = Conexion.getSessionFactory().openSession();
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
        Session session = Conexion.getSessionFactory().openSession();
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

    public List<OrdenTrabajoLaboreo> getLaboreosByCampIdPlanificadaId(Integer id){
        Session session = Conexion.getSessionFactory().openSession();
        List<OrdenTrabajoLaboreo> listaLaboreoLoteEntity = new ArrayList<>();
        LaboreoEntity laboreo = new LaboreoEntity();
        LoteEntity lote = new LoteEntity();
        Query query = session.createQuery("select x.laboreo, x.tipoGrano, x.detallePlanificacionCampaniaLote.lote from DetallePlanificacionCampaniaLaboreosEntity x " +
                "where ucase(x.detallePlanificacionCampaniaLote.planificacion.planificacionId) like ucase(:pId) " +
                "and x.fechaBaja is null and x.tieneOrdenTrabajo = false " +
                "and x.detallePlanificacionCampaniaLote.planificacion.campania.estado not in ('FINALIZADA', 'CANCELADA') ");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        Integer i =0;
        while (iter.hasNext()) {
            OrdenTrabajoLaboreo orden = new OrdenTrabajoLaboreo();
            Object[] array = (Object[]) iter.next();
            orden.setLaboreoEntity((LaboreoEntity)array[0]);
            orden.setSemilla((TipoGranoEntity) array[1]);
            orden.setLoteEntity((LoteEntity) array[2]);
            orden.setSecuencia(i+1);
            i++;
            listaLaboreoLoteEntity.add(orden);
        }
        session.close();
        return listaLaboreoLoteEntity;
    }

    public List<OrdenTrabajoLaboreo> getLaboreosByCampIdPlanificadaIdAndLoteId(Integer planId, Integer loteId){
        Session session = Conexion.getSessionFactory().openSession();
        List<OrdenTrabajoLaboreo> listaLaboreoLoteEntity = new ArrayList<>();
        LaboreoEntity laboreo = new LaboreoEntity();
        LoteEntity lote = new LoteEntity();
        Query query = session.createQuery("select x.laboreo, x.tipoGrano, x.detallePlanificacionCampaniaLote.lote from DetallePlanificacionCampaniaLaboreosEntity x " +
                "where ucase(x.detallePlanificacionCampaniaLote.planificacion.planificacionId) like ucase(:pId) and ucase(x.detallePlanificacionCampaniaLote.lote.lteId) like ucase(:lId) " +
                "and x.fechaBaja is null and x.tieneOrdenTrabajo = false");
        query.setParameter("pId", planId);
        query.setParameter("lId", loteId);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            OrdenTrabajoLaboreo orden = new OrdenTrabajoLaboreo();
            Object[] array = (Object[]) iter.next();
            orden.setLaboreoEntity((LaboreoEntity)array[0]);
            orden.setSemilla((TipoGranoEntity) array[1]);
            orden.setLoteEntity((LoteEntity) array[2]);

            listaLaboreoLoteEntity.add(orden);
        }
        session.close();
        return listaLaboreoLoteEntity;
    }

    public List<OrdenTrabajoEntity> getOrdenesByPlanificadaId(Integer planId){
        Session session = Conexion.getSessionFactory().openSession();
        List<OrdenTrabajoEntity> listaOrdenes = new ArrayList<>();
        OrdenTrabajoEntity ordenTrabajoEntity = new OrdenTrabajoEntity();

        Query query = session.createQuery("select x from OrdenTrabajoEntity x " +
                "where ucase(x.planificacion.id) like ucase(:pId) " +
                "and x.fechaBaja is null and (x.estaRegistrada = false or x.estaRegistrada = null) " +
                "and x.planificacion.campania.estado not in ('FINALIZADA', 'CANCELADA')");
        query.setParameter("pId", planId);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            ordenTrabajoEntity = (OrdenTrabajoEntity) iter.next();
            listaOrdenes.add(ordenTrabajoEntity);
        }
        session.close();
        return listaOrdenes;
    }

}
