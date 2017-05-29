package Repository;

import Conexion.Conexion;
import Datos.InsumoEntity;
import Datos.LoteCampaniaEntity;
import Datos.LoteEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class LoteCampaniaRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<InsumoEntity> getAllInsumos() {
        List<InsumoEntity> listaInsumo = new ArrayList<>();
        Session session = Conexion.getSessionFactory().openSession();
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


    public LoteEntity getLoteByDenominacion(String denominacion) {
        Session session = Conexion.getSessionFactory().openSession();
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


    public void deleteAllByCampania(int id) {
        Session  session = Conexion.getSessionFactory().openSession();

        LoteCampaniaEntity loteCampania = new LoteCampaniaEntity();
        Query query = session.createQuery("DELETE FROM LoteCampaniaEntity where ucase(lcpCnaId) like ucase(:pId)");
        query.setParameter("pId", id);
        query.executeUpdate();
        session.close();
    }


    public LoteCampaniaEntity getLoteCampaniaById(Long id) {
        Session session = Conexion.getSessionFactory().openSession();
        LoteCampaniaEntity loteCampania = new LoteCampaniaEntity();
        Query query = session.createQuery("select x from LoteCampaniaEntity x where ucase(lcpCnaId) like ucase(:pId) and lcpFechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            loteCampania = (LoteCampaniaEntity) iter.next();
        }
        session.close();
        return loteCampania;
    }


    public List<LoteEntity> getLotesByCampaniaId(int id) {
        Session session = Conexion.getSessionFactory().openSession();
        java.util.List list;
        LoteEntity lote;

        List<LoteEntity> listaLotes = new ArrayList<>();
        Query queryLoteByCampania = session.createQuery("select t.loteByLcpLteId from LoteCampaniaEntity t where t.campaniaByLcpCnaId.cnaId = :pId");
        queryLoteByCampania.setParameter("pId", id);
        list = queryLoteByCampania.list();

        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            lote = (LoteEntity) iter.next();
            listaLotes.add(lote);
        }
        session.close();
        return  listaLotes;
    }


    public List<LoteCampaniaEntity> getLotesCampaniasByLote(int id) {
        Session  session = Conexion.getSessionFactory().openSession();
        java.util.List list;
        LoteCampaniaEntity loteCampania;

        List<LoteCampaniaEntity> listaLotesCampania = new ArrayList<>();
        Query queryLoteByCampania = session.createQuery("select t from LoteCampaniaEntity t where t.loteByLcpLteId.lteId = :pId");
        queryLoteByCampania.setParameter("pId", id);
        list = queryLoteByCampania.list();

        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            loteCampania = (LoteCampaniaEntity) iter.next();
            listaLotesCampania.add(loteCampania);
        }
        session.close();
        return  listaLotesCampania;
    }


}
