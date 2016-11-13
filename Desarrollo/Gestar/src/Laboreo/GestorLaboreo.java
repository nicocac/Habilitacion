package Laboreo;

import Campania.*;
import Conexion.Coneccion;
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
    Session session;

    public List getInsumos() {
        session = Coneccion.getSession();
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
                ins = new Insumo(insumo.getInsNombre(), insumo.getInsDescripcion(), new TipoInsumo(insumo.getTipoInsumoByInsTinId().getTinNombre(), insumo.getTipoInsumoByInsTinId().getTinDescripcion()), insumo.getInsUnidadMedida());
                retorno.add(ins);
            }
        } finally {
            session.close();
        }
        return retorno;
    }

    public List getMaquinaria() {
        session = Coneccion.getSession();
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
        session = Coneccion.getSession();
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
        session = Coneccion.getSession();
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
        session = Coneccion.getSession();
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
        session = Coneccion.getSession();
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
        session = Coneccion.getSession();
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



    public void registrarLaboreo(Campania campania, ArrayList<Lote> listaLotes, ArrayList<DetalleLaboreo> detallesLaboreo,
                                 TipoLaboreoEntity tipoLaboreo, Date fechaInicio, Date fechaFin, String descripcion,
                                 TipoGranoEntity tipoGrano) {

        Session session = Coneccion.getSession();
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

                    for(LoteCampaniaEntity lce: it){
                        LoteCampaniaEntity ltc = (LoteCampaniaEntity) lce;
                        if (ltc.getCampaniaByLcpCnaId().equals(campaniaEntity)) {
                            planificacionCampaniaEntity.setLoteCampania(ltc);
                            planificacionCampaniaEntity.setLaboreo(laboreoEntity);
                        }
                    }

                    listaPlanificacionCampaniaEntity.add(planificacionCampaniaEntity);
                    session.save(planificacionCampaniaEntity);
                }
            }
        }

        laboreoEntity.setLaboreoLoteCampaniasByLboId(listaPlanificacionCampaniaEntity);
        session.update(laboreoEntity);
        try {
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
        }
        session.close();
    }


    //Registrar ingresar acopio
    public void registrarIngresoAcopio(Campania campania, ArrayList<Lote> listaLotes, ArrayList<DetalleLaboreo> detallesLaboreo,
                                 TipoLaboreoEntity tipoLaboreo, Date fechaInicio, Date fechaFin, String descripcion,
                                 TipoGranoEntity tipoGrano) {

        Session session = Coneccion.getSession();
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

                    for(LoteCampaniaEntity lce: it){
                        LoteCampaniaEntity ltc = (LoteCampaniaEntity) lce;
                        if (ltc.getCampaniaByLcpCnaId().equals(campaniaEntity)) {
                            planificacionCampaniaEntity.setLoteCampania(ltc);
                            planificacionCampaniaEntity.setLaboreo(laboreoEntity);
                        }
                    }

                    listaPlanificacionCampaniaEntity.add(planificacionCampaniaEntity);
                    session.save(planificacionCampaniaEntity);
                }
            }
        }

        laboreoEntity.setLaboreoLoteCampaniasByLboId(listaPlanificacionCampaniaEntity);
        session.update(laboreoEntity);
        try {
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
        }
        session.close();
    }



    public void registrarLaboreoPrecargado(ArrayList<DetalleLaboreo> detallesLaboreo,
                                 TipoLaboreoEntity tipoLaboreo, String descripcion,
                                 TipoGranoEntity tipoGrano, String metrica, String medida, String nombre) {

        Session session = Coneccion.getSession();
        Transaction tx = session.beginTransaction();

        TipoLaboreoEntity tipoLaboreoEntity = new TipoLaboreoEntity();
        TipoGranoEntity tipoGranoEntity;
        DetalleLaboreoEntity detalleLaboreoEntity=null;
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

        for(DetalleLaboreoEntity detalleLaboreo: listaDetallesLaboreoEntity){
            detalleLaboreo.setLaboreoByDboLboId(laboreoEntity);
            session.save(detalleLaboreo);
        }

        try {
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
        }
        session.close();
    }


    public List<Object[]> getInsumosByLaboreo(Long labId) {
        session = Coneccion.getSession();

        java.util.List list;
        Query queryInsumoByLaboreo = session.createQuery("select t.insumoByDboInsId , t.dboCantidadInsumo " +
                                                         "from DetalleLaboreoEntity t " +
                                                         "where t.laboreoByDboLboId.lboId = :pId");
        queryInsumoByLaboreo.setParameter("pId", labId);
        list = queryInsumoByLaboreo.list();

        session.close();

        return  list;
    }


    public List<Object[]> getMaquinariasByLaboreo(Long labId) {
        session = Coneccion.getSession();

        java.util.List list;
        Query queryMaquinariaByLaboreo = session.createQuery("select t.maquinariaByDboMaqId, t.dboCantidadMaquinaria" +
                                                             " from DetalleLaboreoEntity t " +
                                                             "where t.laboreoByDboLboId.lboId = :pId");
        queryMaquinariaByLaboreo.setParameter("pId", labId);
        list = queryMaquinariaByLaboreo.list();
        session.close();

//        return  listaMaquinaria;
        return  list;
    }



    public List<Object[]> getInsumosByLaboreoAndPlanificacion(Integer labId, Integer planificacionId) {
        session = Coneccion.getSession();

        java.util.List list;
        Query queryInsumoByLaboreo = session.createQuery("select t.insumo , t.cantidadInsumo " +
                "from DetalleLaboreosInsumoDePlanificacionCampaniaEntity t " +
                "where (t.laboreo.lboId = :pId AND t.planificacion.id = :p2Id)");
        queryInsumoByLaboreo.setParameter("pId", labId);
        queryInsumoByLaboreo.setParameter("p2Id", planificacionId);
        list = queryInsumoByLaboreo.list();

        session.close();

        return  list;
    }


    public List<Object[]> getMaquinariasByLaboreoAndPlanificacion(Integer labId, Integer planificacionId) {
        session = Coneccion.getSession();

        java.util.List list;
        Query queryMaquinariaByLaboreo = session.createQuery("select t.maquinaria, t.cantidadMaquinaria" +
                " from DetalleLaboreosMaquinariaDePlanificacionCampaniaEntity t " +
                "where (t.laboreo.lboId = :pId AND t.planificacion.id = :p2Id)");
        queryMaquinariaByLaboreo.setParameter("pId", labId);
        queryMaquinariaByLaboreo.setParameter("p2Id", planificacionId);
        list = queryMaquinariaByLaboreo.list();
        session.close();

//        return  listaMaquinaria;
        return  list;
    }

}
