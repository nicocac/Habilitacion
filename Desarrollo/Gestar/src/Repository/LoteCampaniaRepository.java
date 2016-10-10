package Repository;

import Campania.Campania;
import Conexion.Coneccion;
import Datos.CampaniaEntity;
import Datos.InsumoEntity;
import Datos.LoteCampaniaEntity;
import Datos.LoteEntity;
import Lote.Lote;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class LoteCampaniaRepository {
    Session session = Coneccion.getSession();

    public List<InsumoEntity> getAllInsumos(){
        List<InsumoEntity> listaInsumo = new ArrayList<>();
         session = Coneccion.getSession();
        InsumoEntity insumo;
        Query query = session.createQuery("select x from InsumoEntity x");
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (InsumoEntity) iter.next();
            listaInsumo.add(insumo);
        }
        session.close();
        return listaInsumo;
    }


    public LoteEntity getLoteByDenominacion(String denominacion){
         session = Coneccion.getSession();
        LoteEntity lote = new LoteEntity();
        Query query = session.createQuery("select x from LoteEntity x where ucase(lteDenominacion) like ucase(:pNombre) and lteFechaBaja is null");
        query.setParameter("pNombre", denominacion);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            lote = (LoteEntity) iter.next();
        }
        session.close();
        return lote;
    }




    public InsumoEntity getInsumoById(Long id){
         session = Coneccion.getSession();
        InsumoEntity insumo = new InsumoEntity();
        Query query = session.createQuery("select x from InsumoEntity x where ucase(insId) like ucase(:pId) and insFechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (InsumoEntity) iter.next();
        }
        session.close();
        return insumo;
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

}
