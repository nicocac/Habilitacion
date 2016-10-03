package Laboreo;

import Campania.*;
import Conexion.Coneccion;
import Datos.*;
import Insumo.*;
import Lote.*;
import Maquinaria.Maquinaria;
import TipoInsumo.TipoInsumo;
import org.hibernate.Query;
import org.hibernate.Session;
import Maquinaria.*;

import java.sql.Date;
import java.util.*;

/**
 * Created by OWNER on 8/28/2016.
 */
public class GestorLaboreo {


    Session session;
    public List getInsumos(){
        session = Coneccion.getSession();
        java.util.List list;
        InsumoEntity insumo;
        Insumo ins;
        LinkedList retorno = new LinkedList() ;
        try {
            Query query = session.createQuery("select t from InsumoEntity t where insFechaBaja is null");
            list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                insumo = (InsumoEntity) iter.next();
                ins = new Insumo(insumo.getInsNombre(),insumo.getInsDescripcion(),new TipoInsumo(insumo.getTipoInsumoByInsTinId().getTinNombre(),insumo.getTipoInsumoByInsTinId().getTinDescripcion()),insumo.getInsUnidadMedida());
               retorno.add(ins);
            }
        } finally {
            session.close();
        }
        return retorno;
    }

    public List getMaquinaria(){
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

    public List getCampanias(){
        session = Coneccion.getSession();
        java.util.List list;
        LinkedList retorno = new LinkedList();
        LinkedList lkLotes;
        CampaniaEntity camp ;
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

        }catch(Exception e){System.out.print(e.toString());
    }finally {
            session.close();}

        return retorno;

    }

    public List getMomentos(){
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
                te = (TipoLaboreoEntity)iter.next();
                mom = new MomentoLaboreo(te.getTpoNombre(),te.getTpoDescripcion());
                retorno.add(mom);
            }
        }finally {
            session.close();}

        return retorno;
    }

    public TipoLaboreoEntity getMomentoByNombre(String pMomento){
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
                te = (TipoLaboreoEntity)iter.next();
            }
        }finally {
            session.close();}

        return te;
    }

    public void registrarLaboreo(Campania campania, ArrayList<Lote> lotes, ArrayList<DetalleLaboreo>  detalles, MomentoLaboreo momento, Date fecahInicio, Date fechaFin, String descripcion){
        session = Coneccion.getSession();
        LoteCampaniaEntity loteCampaniaEnt;
        CampaniaEntity campEntity;
        TipoLaboreoEntity tipoLaboreo;
        LaboreoLoteCampaniaEntity laboreoLote;
        DetalleLaboreoEntity detalleEntity;
        MaquinariaEntity maquina;
        InsumoEntity insumo;
        ArrayList<DetalleLaboreoEntity> detallesEntity = new ArrayList<DetalleLaboreoEntity>();
        ArrayList<LaboreoLoteCampaniaEntity> arrayLaboreoLote = new ArrayList<LaboreoLoteCampaniaEntity>();
        LaboreoEntity laboreo = new LaboreoEntity();
        GestorCampania gestCamp = new GestorCampania();
        campEntity = gestCamp.getCampaniaByCnaName(campania.getDenominacion());
        for (int i=0; i <detalles.size();i++ ) {
            detalleEntity = new DetalleLaboreoEntity();
            try {
                    String nombre = detalles.get(i).getInsumo().getNombre();
                    GestorInsumo gest = new GestorInsumo();
                    insumo = gest.getInsumoByName(detalles.get(i).getInsumo().getNombre());
                    detalleEntity.setDboInsId(insumo.getInsId());
            }catch(NullPointerException npe){
                GestorMaquinaria gest = new GestorMaquinaria();
                maquina = gest.getMaquinariaByName(detalles.get(i).getMaquinaria().getNombre());
                detalleEntity.setDboMaqId(maquina.getMaqId());
            }
            detalleEntity.setDboCantidad(detalles.get(i).getCantidad());
            detallesEntity.add(detalleEntity);
        }

        for (int i=0; i <lotes.size();i++ ) {
            laboreoLote = new LaboreoLoteCampaniaEntity();
            GestorLote gestLote = new GestorLote();
            LoteEntity lote = gestLote.getLoteByDenominacion(lotes.get(i).getDenominacion());
            List list = this.getLotesCampania(campania);
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Lote lot = (Lote) iter.next();
                LoteEntity loteCamp = gestLote.getLoteByDenominacion(lot.getDenominacion());
                if (loteCamp.getLteId()==lote.getLteId()){
                    Iterator it = loteCamp.getLoteCampaniasByLteId().iterator();
                    while (it.hasNext()) {
                        LoteCampaniaEntity ltc = (LoteCampaniaEntity)iter.next();
                        if(ltc.getCampaniaByLcpCnaId().equals(campEntity)) {
                            laboreoLote.setLoteCampaniaByLlcLcpId(ltc);
                        }
                    }
                    arrayLaboreoLote.add(laboreoLote);
                }
            }
        }
        laboreo.setLaboreoLoteCampaniasByLboId(arrayLaboreoLote);
        laboreo.setDetalleLaboreosByLboId(detallesEntity);
        laboreo.setLboTpoId(this.getMomentoByNombre(momento.getDescripcion()).getTpoId());
        laboreo.setLboFechaHoraInicio(fecahInicio);
        laboreo.setLboFechaHoraFin(fechaFin);
        laboreo.setLboDescripcion(descripcion);
        session.save(laboreo);
    }



}
