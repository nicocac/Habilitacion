package Laboreo;

import Campania.*;
import Conexion.Conexion;
import Datos.*;
import Insumo.*;
import Lote.*;
import Maquinaria.Maquinaria;
import Repository.*;
import TipoInsumo.TipoInsumo;
import org.hibernate.Query;
import org.hibernate.Session;
import Maquinaria.*;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.*;

/**
 * Created by OWNER on 8/28/2016.
 */
public class GestorLaboreo {
    CampaniaRepository campaniaRepository = new CampaniaRepository();
    InsumoRepository insumoRepository = new InsumoRepository();
    MaquinariaRepository maquinariaRepository = new MaquinariaRepository();
    LoteRepository loteRepository = new LoteRepository();
    TipoLaboreoRepository tipoLaboreoRepository = new TipoLaboreoRepository();
    LoteCampaniaRepository loteCampaniaRepository = new LoteCampaniaRepository();
    TipoGranoRepository tipoGranoRepository = new TipoGranoRepository();
    OrdenRepository ordenRepository = new OrdenRepository();
//    Session session;

    public List getInsumos() {
        Session session = Conexion.getSessionFactory().openSession();
        java.util.List list;
        InsumoEntity insumo;
        Insumo ins;
        LinkedList retorno = new LinkedList();
        try {
            Query query = session.createQuery("select t from InsumoEntity t where insFechaBaja is null");
            list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                insumo = (InsumoEntity) iter.next();
                ins = new Insumo(insumo.getInsNombre(), insumo.getInsDescripcion(), new TipoInsumo(insumo.getTipoInsumoByInsTinId().getTinNombre(), insumo.getTipoInsumoByInsTinId().getTinDescripcion()), insumo.getInsUnidadMedida(), insumo.getInsStock(), insumo.getInsStockDisponible());
                retorno.add(ins);
            }
        } finally {
            session.close();
        }
        return retorno;
    }

    public List getMaquinaria() {
        Session session = Conexion.getSessionFactory().openSession();
        java.util.List list;
        MaquinariaEntity maq;
        Maquinaria maquinaria;
        LinkedList retorno = new LinkedList();
        try {
            Query query = session.createQuery("select t from MaquinariaEntity t where maqFechaBaja is null");
            list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                maq = (MaquinariaEntity) iter.next();
                maquinaria = new Maquinaria(maq.getMaqNombre(), maq.getMaqDescripcion(), maq.getMaqMarca(), maq.getMaqModelo(), maq.getMaqAnioFabricacion(), new TipoMaquinaria(maq.getTipoMaquinariaByMaqTmaqId().getTmaNombre(), maq.getTipoMaquinariaByMaqTmaqId().getTmaDescripcion()), new EstadoMaquinaria(maq.getTipoEstadoMaquinariaByMaqTestadoId().getTeMaNombre(), maq.getTipoEstadoMaquinariaByMaqTestadoId().getTeMaDescripcion()));
                retorno.add(maquinaria);
            }
        } finally {
            session.close();
        }
        return retorno;
    }

    public List getCampanias() {
        Session session = Conexion.getSessionFactory().openSession();
        java.util.List list;
        LinkedList retorno = new LinkedList();
        LinkedList lkLotes;
        CampaniaEntity camp;
        Campania campania = new Campania();
        try {
            Query query = session.createQuery("select t from CampaniaEntity t where cnaFechaBaja is null");
            list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                camp = (CampaniaEntity) iter.next();
                Collection lotes = camp.getLoteCampaniasByCnaId();
                Iterator iterLotes = lotes.iterator();
                lkLotes = new LinkedList();
                while (iterLotes.hasNext()) {
                    // busco por cada lote de campania suy lote correspondiente
                    LoteCampaniaEntity loteCamp = (LoteCampaniaEntity) iterLotes.next();
                    // busco el lote correspondinte a ese lote de campania
                    LoteEntity lote = loteCamp.getLoteByLcpLteId();
                    // genero el lote
                    lkLotes.add(new Lote(lote.getLteDenominacion(), lote.getLteCantMetros(), lote.getLteUbicacion(), lote.getLteFechaDesde(), lote.getLteFechaHasta()));

                }
                campania = new Campania(camp.getCnaDenominacion(), camp.getCnaFechaInicio(), camp.getCnaFechaFinEstimada(), camp.getCnaFechaFinReal(), lkLotes);
                retorno.add(campania);
            }
        } finally {
            session.close();
        }
        return retorno;
    }

    public List getLotesCampania(Campania camp) {
        Session session = Conexion.getSessionFactory().openSession();
        java.util.List list;
        java.util.Collection col;
        LinkedList retorno = new LinkedList();
        CampaniaEntity campEnt = new CampaniaEntity();
        LoteCampaniaEntity lcp;
        LoteEntity loteEnt;
        Lote lote;
        try {
            Query queryCamp = session.createQuery("select t from CampaniaEntity t where cnaDenominacion = :pNombre");
            queryCamp.setParameter("pNombre", camp.getDenominacion());
            list = queryCamp.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                campEnt = (CampaniaEntity) iter.next();
            }

            col = campEnt.getLoteCampaniasByCnaId();
            //Query queryLcp = session.createQuery("select t from LoteCampaniaEntity t where t.lcpFechaBaja is null ");
            //queryLcp.setParameter("pCnaId", campEnt.getCnaId());
            //col = queryLcp.list();
            iter = col.iterator();
            while (iter.hasNext()) {
                lcp = (LoteCampaniaEntity) iter.next();
                lote = new Lote(lcp.getLoteByLcpLteId().getLteDenominacion(), lcp.getLoteByLcpLteId().getLteCantMetros(), lcp.getLoteByLcpLteId().getLteUbicacion(), lcp.getLoteByLcpLteId().getLteFechaDesde(), lcp.getLoteByLcpLteId().getLteFechaHasta());
                retorno.add(lote);
            }

        } catch (Exception e) {
            System.out.print(e.toString());
        } finally {
            session.close();
        }

        return retorno;

    }

    public List getMomentos() {
        Session session = Conexion.getSessionFactory().openSession();
        java.util.List list;
        LinkedList retorno = new LinkedList();
        TipoLaboreoEntity te;
        MomentoLaboreo mom;
        try {
            Query query = session.createQuery("select t from TipoLaboreoEntity t where tpoFechaBaja is null");
            list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                te = (TipoLaboreoEntity) iter.next();
                mom = new MomentoLaboreo(te.getTpoNombre(), te.getTpoDescripcion());
                retorno.add(mom);
            }
        } finally {
            session.close();
        }

        return retorno;
    }


    public List<TipoGranoEntity> getSemillas() {
        Session session = Conexion.getSessionFactory().openSession();
        java.util.List list;
//        LinkedList retorno = new LinkedList();
        List<TipoGranoEntity> listaTipoGrano = new ArrayList<>();
        TipoGranoEntity tg;
        try {
            Query query = session.createQuery("select t from TipoGranoEntity t where tgrFechaBaja is null");
            list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                tg = (TipoGranoEntity) iter.next();
                listaTipoGrano.add(tg);
            }
        } finally {
            session.close();
        }

        return listaTipoGrano;
    }


    public TipoLaboreoEntity getMomentoByNombre(String pMomento) {
        Session session = Conexion.getSessionFactory().openSession();
        java.util.List list;
        LinkedList retorno = new LinkedList();
        TipoLaboreoEntity te = new TipoLaboreoEntity();
        MomentoLaboreo mom;
        try {
            Query query = session.createQuery("select t from TipoLaboreoEntity t where t.tpoDescripcion = :pMomento and tpoFechaBaja is null");
            query.setParameter("pMomento", pMomento);
            list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                te = (TipoLaboreoEntity) iter.next();
            }
        } finally {
            session.close();
        }

        return te;
    }


    public void registrarLaboreo(Planificacion planificacion, Date fechaPlan, String descripcion) {

        Session session = Conexion.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        CampaniaEntity campaniaEntity;

        PlanificacionCampaniaEntity planificacionCampaniaEntity = new PlanificacionCampaniaEntity();
        DetallePlanificacionCampaniaLoteEntity detallePlanificacionCampaniaLoteEntity;
        DetallePlanificacionCampaniaLaboreosEntity detallePlanificacionCampaniaLaboreosEntity;
        DetalleLaboreosInsumoDePlanificacionCampaniaEntity detalleLaboreosInsumoDePlanificacionCampaniaEntity;
        DetalleLaboreosMaquinariaDePlanificacionCampaniaEntity detalleLaboreosMaquinariaDePlanificacionCampaniaEntity;

        MaquinariaEntity maquinariaEntity;
        InsumoEntity insumoEntity;

        ArrayList<DetallePlanificacionCampaniaLoteEntity> listaLotes = new ArrayList<>();
        ArrayList<DetallePlanificacionCampaniaLaboreosEntity> listaLaboreos = new ArrayList<>();
        ArrayList<DetalleLaboreosInsumoDePlanificacionCampaniaEntity> listaInsumos = new ArrayList<>();
        ArrayList<DetalleLaboreosMaquinariaDePlanificacionCampaniaEntity> listaMaquinarias = new ArrayList<>();

        planificacionCampaniaEntity.setCampania(planificacion.getCampania());
        planificacionCampaniaEntity.setFechaAlta(fechaPlan);
        planificacionCampaniaEntity.setObservaciones(descripcion);

        for (DetalleLote lote : planificacion.getListaDetallesLote()) {
            detallePlanificacionCampaniaLoteEntity = new DetallePlanificacionCampaniaLoteEntity();
            detallePlanificacionCampaniaLoteEntity.setLote(lote.getLoteEntity());
            LoteEntity loteEntity = lote.getLoteEntity();
            loteEntity.setEstado("OCUPADO");
            try {
                session.update(loteEntity);

            } catch (Exception ex) {
                tx.rollback();
                session.close();
            }

            detallePlanificacionCampaniaLoteEntity.setPlanificacion(planificacionCampaniaEntity);
            detallePlanificacionCampaniaLoteEntity.setObservaciones("Carga de Lotes");
            detallePlanificacionCampaniaLoteEntity.setFechaAlta(fechaPlan);

            for (DetalleLaboreos laboreo : lote.getListaDetalleLaboreos()) {
                detallePlanificacionCampaniaLaboreosEntity = new DetallePlanificacionCampaniaLaboreosEntity();
                detallePlanificacionCampaniaLaboreosEntity.setLaboreo(laboreo.getLaboreoEntity());
                detallePlanificacionCampaniaLaboreosEntity.setDetallePlanificacionCampaniaLote(detallePlanificacionCampaniaLoteEntity);
                detallePlanificacionCampaniaLaboreosEntity.setObservaciones("Carga Laboreos");
                detallePlanificacionCampaniaLaboreosEntity.setFechaAlta(fechaPlan);
                detallePlanificacionCampaniaLaboreosEntity.setTipoGrano(laboreo.getTipoGranoEntity());
                detallePlanificacionCampaniaLaboreosEntity.setTieneOrdenTrabajo(false);

                for (DetalleLaboreo insOmaq : laboreo.getListaDetalleLaboreo()) {

                    try {
                        String nombreInsumo = insOmaq.getInsumo().getNombre();
                        insumoEntity = insumoRepository.getInsumoByNombre(nombreInsumo);
                        Long stockDisponible = ((insumoEntity.getInsStockDisponible()==null)? 0: insumoEntity.getInsStockDisponible());
                        insumoEntity.setInsStockDisponible(stockDisponible - insOmaq.getCantidadIsumo());
                        session.update(insumoEntity);


                        detalleLaboreosInsumoDePlanificacionCampaniaEntity = new DetalleLaboreosInsumoDePlanificacionCampaniaEntity();
                        detalleLaboreosInsumoDePlanificacionCampaniaEntity.setInsumo(insumoEntity);
                        detalleLaboreosInsumoDePlanificacionCampaniaEntity.setCantidadInsumo(insOmaq.getCantidadIsumo());
                        detalleLaboreosInsumoDePlanificacionCampaniaEntity.setFechaAlta(fechaPlan);
                        detalleLaboreosInsumoDePlanificacionCampaniaEntity.setObservaciones("Carga Insumo del laboreo");
                        detalleLaboreosInsumoDePlanificacionCampaniaEntity.setDetallePlanificacionCampaniaLaboreos(detallePlanificacionCampaniaLaboreosEntity);

                        listaInsumos.add(detalleLaboreosInsumoDePlanificacionCampaniaEntity);

                    } catch (NullPointerException npe) {
                        String nombreMaquinaria = insOmaq.getMaquinaria().getNombre();
                        maquinariaEntity = maquinariaRepository.getMaquinariaByNombre(nombreMaquinaria);

                        detalleLaboreosMaquinariaDePlanificacionCampaniaEntity = new DetalleLaboreosMaquinariaDePlanificacionCampaniaEntity();
                        detalleLaboreosMaquinariaDePlanificacionCampaniaEntity.setMaquinaria(maquinariaEntity);
                        detalleLaboreosMaquinariaDePlanificacionCampaniaEntity.setCantidadMaquinaria(insOmaq.getCantidadMaquinaria());
                        detalleLaboreosMaquinariaDePlanificacionCampaniaEntity.setFechaAlta(fechaPlan);
                        detalleLaboreosMaquinariaDePlanificacionCampaniaEntity.setObservaciones("Carga Maquinaria del laboreo");
                        detalleLaboreosMaquinariaDePlanificacionCampaniaEntity.setDetallePlanificacionCampaniaLaboreos(detallePlanificacionCampaniaLaboreosEntity);

                        listaMaquinarias.add(detalleLaboreosMaquinariaDePlanificacionCampaniaEntity);
                    }
                }
                listaLaboreos.add(detallePlanificacionCampaniaLaboreosEntity);
            }
            listaLotes.add(detallePlanificacionCampaniaLoteEntity);
        }


        session.save(planificacionCampaniaEntity);

        for (DetallePlanificacionCampaniaLoteEntity lote : listaLotes) {
            session.save(lote);
            for (DetallePlanificacionCampaniaLaboreosEntity laboreo : listaLaboreos) {
                session.save(laboreo);

            }
        }

        for (DetalleLaboreosInsumoDePlanificacionCampaniaEntity ins : listaInsumos) {
            session.save(ins);
        }
        for (DetalleLaboreosMaquinariaDePlanificacionCampaniaEntity maq : listaMaquinarias) {
            session.save(maq);
        }


        session.update(planificacionCampaniaEntity);

        campaniaEntity = planificacion.getCampania();
        campaniaEntity.setEstaPlanificada(true);
        campaniaEntity.setEstado("PLANIFICADA");
        session.update(campaniaEntity);

        try {
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            session.close();

        }
        session.close();
    }


    //Registrar ingresar acopio

    public void registrarIngresoAcopio(Campania campania, ArrayList<Lote> listaLotes, ArrayList<DetalleLaboreo> detallesLaboreo,
                                       TipoLaboreoEntity tipoLaboreo, Date fechaInicio, Date fechaFin, String descripcion,
                                       TipoGranoEntity tipoGrano) {

        Session session = Conexion.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        LoteCampaniaEntity loteCampaniaEntity;
        CampaniaEntity campaniaEntity;
        TipoLaboreoEntity tipoLaboreoEntity = new TipoLaboreoEntity();
        TipoGranoEntity tipoGranoEntity;
        PlanificacionCampaniaEntity planificacionCampaniaEntity;
        DetalleLaboreoEntity detalleLaboreoEntity;
        MaquinariaEntity maquinariaEntity;
        InsumoEntity insumoEntity;
        LaboreoEntity laboreoEntity = new LaboreoEntity();


        ArrayList<DetalleLaboreoEntity> listaDetallesLaboreoEntity = new ArrayList<>();
        for (int i = 0; i < detallesLaboreo.size(); i++) {
            detalleLaboreoEntity = new DetalleLaboreoEntity();
            try {
                String nombreInsumo = detallesLaboreo.get(i).getInsumo().getNombre();
                insumoEntity = insumoRepository.getInsumoByNombre(nombreInsumo);
                detalleLaboreoEntity.setInsumoByDboInsId(insumoEntity);

            } catch (NullPointerException npe) {
                String nombreMaquinaria = detallesLaboreo.get(i).getMaquinaria().getNombre();
                maquinariaEntity = maquinariaRepository.getMaquinariaByNombre(nombreMaquinaria);
                detalleLaboreoEntity.setMaquinariaByDboMaqId(maquinariaEntity);
            }
//            detalleLaboreoEntity.setDboCantidad(detallesLaboreo.get(i).getCantidad());
            listaDetallesLaboreoEntity.add(detalleLaboreoEntity);

//            session.save(detalleLaboreoEntity);

        }


        tipoLaboreoEntity = tipoLaboreoRepository.getTipoLaboreoByNombre(tipoLaboreo.getTpoNombre());
        tipoGranoEntity = tipoGranoRepository.getTipoGranoByNombre(tipoGrano.getTgrNombre());
        laboreoEntity.setTipoGrano(tipoGranoEntity);
        laboreoEntity.setDetalleLaboreosByLboId(listaDetallesLaboreoEntity);
        laboreoEntity.setTipoLaboreoEntity(tipoLaboreoEntity);
        laboreoEntity.setLboFechaHoraInicio(fechaInicio);
        laboreoEntity.setLboFechaHoraFin(fechaFin);
        laboreoEntity.setLboDescripcion(descripcion);
        session.save(laboreoEntity);


        ArrayList<PlanificacionCampaniaEntity> listaPlanificacionCampaniaEntity = new ArrayList<>();
        campaniaEntity = campaniaRepository.getCampaniaByNombre(campania.getDenominacion());

        for (int i = 0; i < listaLotes.size(); i++) {
            planificacionCampaniaEntity = new PlanificacionCampaniaEntity();

            LoteEntity loteEntity = loteRepository.getLoteByDenominacion(listaLotes.get(i).getDenominacion());
            List list = this.getLotesCampania(campania);

            Iterator iter = list.iterator();

            while (iter.hasNext()) {
                Lote lot = (Lote) iter.next();
                LoteEntity loteCamp = loteRepository.getLoteByDenominacion(lot.getDenominacion());

                if (loteCamp.getLteId() == loteEntity.getLteId()) {

//                    Iterator it = loteCamp.getLoteCampaniasByLteId().iterator();
                    List<LoteCampaniaEntity> it = loteCampaniaRepository.getLotesCampaniasByLote(loteCamp.getLteId());

                    for (LoteCampaniaEntity lce : it) {
                        LoteCampaniaEntity ltc = (LoteCampaniaEntity) lce;
                        if (ltc.getCampaniaByLcpCnaId().equals(campaniaEntity)) {
                            planificacionCampaniaEntity.setLoteCampania(ltc);
//                            planificacionCampaniaEntity.setLaboreo(laboreoEntity);
                        }
                    }

                    listaPlanificacionCampaniaEntity.add(planificacionCampaniaEntity);
                    session.save(planificacionCampaniaEntity);
                }
            }
        }

//        laboreoEntity.setLaboreoLoteCampaniasByLboId(listaPlanificacionCampaniaEntity);
        session.update(laboreoEntity);
        try {
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
        }
        session.close();
    }


    public void registrarLaboreoPrecargado(ArrayList<DetalleLaboreo> detallesLaboreo,
                                           TipoLaboreoEntity tipoLaboreo, String descripcion,
                                           TipoGranoEntity tipoGrano, String metrica, String medida, String nombre) {

        Session session = Conexion.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        TipoLaboreoEntity tipoLaboreoEntity = new TipoLaboreoEntity();
        TipoGranoEntity tipoGranoEntity;
        DetalleLaboreoEntity detalleLaboreoEntity = null;
        MaquinariaEntity maquinariaEntity;
        InsumoEntity insumoEntity;
        LaboreoEntity laboreoEntity = new LaboreoEntity();


        ArrayList<DetalleLaboreoEntity> listaDetallesLaboreoEntity = new ArrayList<>();
        for (int i = 0; i < detallesLaboreo.size(); i++) {
            detalleLaboreoEntity = new DetalleLaboreoEntity();
            try {
                String nombreInsumo = detallesLaboreo.get(i).getInsumo().getNombre();
                insumoEntity = insumoRepository.getInsumoByNombre(nombreInsumo);
                detalleLaboreoEntity.setInsumoByDboInsId(insumoEntity);
                detalleLaboreoEntity.setDboCantidadInsumo(detallesLaboreo.get(i).getCantidadIsumo());


            } catch (NullPointerException npe) {
                String nombreMaquinaria = detallesLaboreo.get(i).getMaquinaria().getNombre();
                maquinariaEntity = maquinariaRepository.getMaquinariaByNombre(nombreMaquinaria);
                detalleLaboreoEntity.setMaquinariaByDboMaqId(maquinariaEntity);
                detalleLaboreoEntity.setDboCantidadMaquinaria(detallesLaboreo.get(i).getCantidadMaquinaria());
                session.update(maquinariaEntity);

            }
            listaDetallesLaboreoEntity.add(detalleLaboreoEntity);

//            session.save(detalleLaboreoEntity);

        }

        laboreoEntity.setMedida(medida);
        laboreoEntity.setMetrica(metrica);
        laboreoEntity.setLboNombre(nombre);

        tipoLaboreoEntity = tipoLaboreoRepository.getTipoLaboreoByNombre(tipoLaboreo.getTpoNombre());
        tipoGranoEntity = tipoGranoRepository.getTipoGranoByNombre(tipoGrano.getTgrNombre());
        laboreoEntity.setTipoGrano(tipoGranoEntity);
        laboreoEntity.setDetalleLaboreosByLboId(listaDetallesLaboreoEntity);
        laboreoEntity.setTipoLaboreoEntity(tipoLaboreoEntity);
//        laboreoEntity.setLboFechaHoraInicio(fechaInicio);
//        laboreoEntity.setLboFechaHoraFin(fechaFin);
        laboreoEntity.setLboDescripcion(descripcion);
        session.save(laboreoEntity);

        for (DetalleLaboreoEntity detalleLaboreo : listaDetallesLaboreoEntity) {
            detalleLaboreo.setLaboreoByDboLboId(laboreoEntity);
            session.save(detalleLaboreo);
        }

        try {
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
        }
        session.close();
    }


    public void registrarOrden(ArrayList<DetalleLaboreo> detallesLaboreo, String nroOrden, PlanificacionCampaniaEntity planificacion,
                               String txtRRHH, Date fecha, String txtTiempo, LaboreoEntity laboreoEntity,
                               LoteEntity loteEntity, TipoGranoEntity tipoGranoEntity) {

        Session session = Conexion.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        OrdenTrabajoEntity ordenTrabajoEntity = new OrdenTrabajoEntity();
        DetalleOrdenEntity detalleOrdenEntity;

        InsumoEntity insumoEntity;
        MaquinariaEntity maquinariaEntity;

        ArrayList<DetalleOrdenEntity> listaDetallesLaboreoEntity = new ArrayList<>();
        for (int i = 0; i < detallesLaboreo.size(); i++) {
            detalleOrdenEntity = new DetalleOrdenEntity();
            detalleOrdenEntity.setObservaciones("Carga detalles de una orden de trabajo");
            try {
                String nombreInsumo = detallesLaboreo.get(i).getInsumo().getNombre();
                insumoEntity = insumoRepository.getInsumoByNombre(nombreInsumo);
                detalleOrdenEntity.setInsumo(insumoEntity);
                detalleOrdenEntity.setCantidadInsumo(detallesLaboreo.get(i).getCantidadIsumo());


            } catch (NullPointerException npe) {
                String nombreMaquinaria = detallesLaboreo.get(i).getMaquinaria().getNombre();
                maquinariaEntity = maquinariaRepository.getMaquinariaByNombre(nombreMaquinaria);
                detalleOrdenEntity.setMaquinaria(maquinariaEntity);
                detalleOrdenEntity.setCantidadMaquinaria(detallesLaboreo.get(i).getCantidadMaquinaria());
//                session.update(maquinariaEntity);

            }
            listaDetallesLaboreoEntity.add(detalleOrdenEntity);

        }


        ordenTrabajoEntity.setObservaciones("Carga de una orden");
        ordenTrabajoEntity.setNroOrden(Integer.parseInt(nroOrden));
        ordenTrabajoEntity.setPlanificacion(planificacion);
        ordenTrabajoEntity.setRecursoHumano(txtRRHH);
        ordenTrabajoEntity.setFechaAlta(fecha);
        ordenTrabajoEntity.setTiempo(txtTiempo);
        ordenTrabajoEntity.setLaboreo(laboreoEntity);
        ordenTrabajoEntity.setLote(loteEntity);
        ordenTrabajoEntity.setGrano(tipoGranoEntity);

        session.save(ordenTrabajoEntity);

        for (DetalleOrdenEntity detalleLaboreo : listaDetallesLaboreoEntity) {
            detalleLaboreo.setOrden(ordenTrabajoEntity);
            session.save(detalleLaboreo);
        }

        DetallePlanificacionCampaniaLaboreosEntity detallePlanificacionCampaniaLaboreosEntity =
                getLaboreoByPlanificacionAndLote(laboreoEntity.getLboId(), planificacion.getPlanificacionId(), loteEntity.getLteId());
        detallePlanificacionCampaniaLaboreosEntity.setTieneOrdenTrabajo(true);
        session.update(detallePlanificacionCampaniaLaboreosEntity);

        try {
            tx.commit();
            session.flush();
        } catch (Exception ex) {
            tx.rollback();
        } finally {
            session.close();
        }
    }


    public void registrarAvanceOrdenIngreso(Date fecha, ArrayList<DetalleLaboreo> detallesLaboreo, OrdenTrabajoEntity orden,
                                            String txtRRHH, String txtTiempo, String cantidad, String estadoSemilla, AcopioEntity acopioEntity, Integer cantidadP, String porcentaje) {

        Session session = Conexion.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

//        Date fecha = (Date) session.createSQLQuery("SELECT SYSDATE CURRENT_DATE FROM DUAL").uniqueResult();

        DetalleOrdenEntity detalleOrdenEntity;

        InsumoEntity insumoEntity;
        MaquinariaEntity maquinariaEntity;

        ArrayList<DetalleOrdenEntity> listaDetallesLaboreoEntity = new ArrayList<>();
        for (int i = 0; i < detallesLaboreo.size(); i++) {
            detalleOrdenEntity = new DetalleOrdenEntity();
            detalleOrdenEntity.setObservaciones("Carga detalles reales de una orden de trabajo registrada");
            try {
                String nombreInsumo = detallesLaboreo.get(i).getInsumo().getNombre();
                insumoEntity = insumoRepository.getInsumoByNombre(nombreInsumo);

                Long stockDisponible = ((insumoEntity.getInsStockDisponible()==null)? 0: insumoEntity.getInsStockDisponible());
                insumoEntity.setInsStockDisponible(stockDisponible - detallesLaboreo.get(i).getCantidadIsumo());

                session.update(insumoEntity);

                detalleOrdenEntity.setInsumo(insumoEntity);
                detalleOrdenEntity.setCantidadInsumo(detallesLaboreo.get(i).getCantidadIsumo());


            } catch (NullPointerException npe) {
                String nombreMaquinaria = detallesLaboreo.get(i).getMaquinaria().getNombre();
                maquinariaEntity = maquinariaRepository.getMaquinariaByNombre(nombreMaquinaria);
                detalleOrdenEntity.setMaquinaria(maquinariaEntity);
                detalleOrdenEntity.setCantidadMaquinaria(detallesLaboreo.get(i).getCantidadMaquinaria());
//                session.update(maquinariaEntity);

            }
            listaDetallesLaboreoEntity.add(detalleOrdenEntity);

        }

        String obs = orden.getObservaciones();
        if (obs == null) {
            obs = "";
        }
        orden.setObservaciones(obs + " - Ingreso temp ");
        orden.setRecursoHumano(txtRRHH);
        orden.setFechaAlta(fecha);
        String tiempo = orden.getTiempoGastado();
        if (tiempo == null) {
            tiempo = txtTiempo;
        } else {
            Integer tiempoInt = Integer.parseInt(tiempo) + Integer.parseInt(txtTiempo);
            tiempo = tiempoInt.toString();
        }
        orden.setTiempoGastado(tiempo);

        Integer cantidadPTemp = orden.getCantidadP();
        String porcentajeTemp = orden.getPorcentaje();

        if (cantidadPTemp == null) {
            cantidadPTemp = cantidadP;
        } else {
            cantidadPTemp += cantidadP;
        }
        orden.setCantidadP(cantidadPTemp);

        if (porcentajeTemp == null) {
            porcentajeTemp = porcentaje;
        } else {
            Integer porcentajeInt = Integer.parseInt(porcentajeTemp) + Integer.parseInt(porcentaje);
            porcentajeTemp = porcentajeInt.toString();
        }
        orden.setPorcentaje(porcentajeTemp);

//        orden.setEstaRegistrada(true);

        session.update(orden);

        //Borramos los detalles viejos
//        int delete = ordenRepository.deleteAllDetalleOrdenesByOrdenID(orden.getId());
        int result = 0;
        Query queryDelete = session.createQuery("delete from DetalleOrdenEntity x " +
                "where x.orden.id = (:ordenID) ");
        queryDelete.setParameter("ordenID", orden.getId());
        result = queryDelete.executeUpdate();

        if (result > 0) {
            //Guardamos la orden a los detalles nuevo
            for (DetalleOrdenEntity detalleLaboreo : listaDetallesLaboreoEntity) {
                detalleLaboreo.setOrden(orden);
                session.save(detalleLaboreo);
            }
        }


        IngresoAcopioEntity ingresoAcopioEntity = new IngresoAcopioEntity();
        if(acopioEntity != null) {
            ingresoAcopioEntity.setAcopio(acopioEntity);
        }
        ingresoAcopioEntity.setTipoGrano(orden.getGrano());
        ingresoAcopioEntity.setLaboreo(orden.getLaboreo());
        ingresoAcopioEntity.setCampania(orden.getPlanificacion().getCampania());
        ingresoAcopioEntity.setLote(orden.getLote());
        if(!cantidad.equals("")) {
            ingresoAcopioEntity.setIngresoCantidadTotal(Integer.parseInt(cantidad));
        }
        ingresoAcopioEntity.setEstadoSemilla(estadoSemilla);
        ingresoAcopioEntity.setIngresoFecha(fecha);
        ingresoAcopioEntity.setIngresoUsuarioAlta("admin");

        session.save(ingresoAcopioEntity);

        try {
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
        }
        session.close();
    }


    public void registrarEgresoSemillas(ArrayList<DetalleEgresoAcopioEntity> detallesEgreso,
                                        AcopioEntity acopio, EgresoAcopioEntity egresoAcopio,
                                        TipoGranoEntity tipoGrano) {

        Session session = Conexion.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(egresoAcopio);

        for (DetalleEgresoAcopioEntity detalle : detallesEgreso) {
            detalle.setEgresoAcopio(egresoAcopio);
            session.save(detalle);
        }

        try {
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
        }
        session.close();
    }


    public List<Object[]> getInsumosByLaboreo(Long labId) {
        Session session = Conexion.getSessionFactory().openSession();

        java.util.List list;
        Query queryInsumoByLaboreo = session.createQuery("select t.insumoByDboInsId , t.dboCantidadInsumo " +
                "from DetalleLaboreoEntity t " +
                "where t.laboreoByDboLboId.lboId = :pId");
        queryInsumoByLaboreo.setParameter("pId", labId);
        list = queryInsumoByLaboreo.list();

        session.close();

        return list;
    }


    public List<Object[]> getMaquinariasByLaboreo(Long labId) {
        Session session = Conexion.getSessionFactory().openSession();

        java.util.List list;
        Query queryMaquinariaByLaboreo = session.createQuery("select t.maquinariaByDboMaqId, t.dboCantidadMaquinaria" +
                " from DetalleLaboreoEntity t " +
                "where t.laboreoByDboLboId.lboId = :pId");
        queryMaquinariaByLaboreo.setParameter("pId", labId);
        list = queryMaquinariaByLaboreo.list();
        session.close();

//        return  listaMaquinaria;
        return list;
    }


    public List<Object[]> getInsumosByLaboreoAndPlanificacion(Long labId, Integer planificacionId) {
        Session session = Conexion.getSessionFactory().openSession();

        java.util.List list;
        Query queryInsumoByLaboreo = session.createQuery("select t.insumo , t.cantidadInsumo " +
                "from DetalleLaboreosInsumoDePlanificacionCampaniaEntity t " +
                "where (t.detallePlanificacionCampaniaLaboreos.laboreo.id = :pId" +
                " AND t.detallePlanificacionCampaniaLaboreos.detallePlanificacionCampaniaLote.planificacion.id = :p2Id)");
        queryInsumoByLaboreo.setParameter("pId", labId);
        queryInsumoByLaboreo.setParameter("p2Id", planificacionId);
        list = queryInsumoByLaboreo.list();

        session.close();

        return list;
    }


    public List<Object[]> getMaquinariasByLaboreoAndPlanificacion(Long labId, Integer planificacionId) {
        Session session = Conexion.getSessionFactory().openSession();

        java.util.List list;
        Query queryMaquinariaByLaboreo = session.createQuery("select t.maquinaria, t.cantidadMaquinaria" +
                " from DetalleLaboreosMaquinariaDePlanificacionCampaniaEntity t " +
                "where (t.detallePlanificacionCampaniaLaboreos.laboreo.id = :pId" +
                " AND t.detallePlanificacionCampaniaLaboreos.detallePlanificacionCampaniaLote.planificacion.id = :p2Id)");
        queryMaquinariaByLaboreo.setParameter("pId", labId);
        queryMaquinariaByLaboreo.setParameter("p2Id", planificacionId);
        list = queryMaquinariaByLaboreo.list();
        session.close();

//        return  listaMaquinaria;
        return list;
    }

    public DetallePlanificacionCampaniaLaboreosEntity getLaboreoByPlanificacionAndLote(Long labId, Integer planificacionId, Integer loteId) {
        Session session = Conexion.getSessionFactory().openSession();

        DetallePlanificacionCampaniaLaboreosEntity detallePlanificacionCampaniaLaboreosEntity;
        java.util.List list;
        Query queryInsumoByLaboreo = session.createQuery("select t " +
                "from DetallePlanificacionCampaniaLaboreosEntity t " +
                "where (t.laboreo.id = :pId" +
                " AND t.detallePlanificacionCampaniaLote.planificacion.id = :p2Id AND t.detallePlanificacionCampaniaLote.lote.id = :p3Id)");
        queryInsumoByLaboreo.setParameter("pId", labId);
        queryInsumoByLaboreo.setParameter("p2Id", planificacionId);
        queryInsumoByLaboreo.setParameter("p3Id", loteId);
        detallePlanificacionCampaniaLaboreosEntity = (DetallePlanificacionCampaniaLaboreosEntity) queryInsumoByLaboreo.uniqueResult();

        session.close();

        return detallePlanificacionCampaniaLaboreosEntity;
    }


    public List<Object[]> getStockByAcopio() {
        Session session = Conexion.getSessionFactory().openSession();

        java.util.List<Object[]> list;

        Query query = session.createQuery("select a.acopioId, a.nombre, a.codigo, a.tipoAcopioEntity.tipoAcopioNombre," +
                " i.tipoGrano.tgrNombre, i.estadoSemilla, SUM(i.ingresoCantidadTotal) " +
                "from AcopioEntity a, IngresoAcopioEntity i " +
                "where (a.acopioId = i.acopio.acopioId)" +
                "group by a.acopioId, a.nombre, a.codigo, a.tipoAcopioEntity.tipoAcopioNombre, i.tipoGrano.tgrNombre, i.estadoSemilla");

        list = query.list();

        session.close();

        return list;
    }


    public List<Object[]> getStockIngresoByAcopio() {
        Session session = Conexion.getSessionFactory().openSession();

        java.util.List<Object[]> list;

        Query query = session.createQuery("select a.acopioId, SUM(i.ingresoCantidadTotal) " +
                "from AcopioEntity a, IngresoAcopioEntity i " +
                "where (a.acopioId = i.acopio.acopioId)" +
                "group by a.acopioId ");

        list = query.list();

        session.close();

        return list;
    }


    public List<Object[]> getStockEgresoByAcopio() {
        Session session = Conexion.getSessionFactory().openSession();

        java.util.List<Object[]> list;

        Query query = session.createQuery("select a.acopioId, SUM(d.detalleEgresoCantidad) " +
                "from AcopioEntity a, DetalleEgresoAcopioEntity d " +
                "where (a.acopioId = d.acopio.acopioId)" +
                "group by a.acopioId ");

        list = query.list();

        session.close();

        return list;
    }

}
