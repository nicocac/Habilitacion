package Lote;

import Conexion.Conexion;
import Datos.LoteCampaniaEntity;
import Datos.LoteEntity;
import Laboreo.MomentoLaboreo;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Iterator;

/**
 * Created by OWNER on 10/2/2016.
 */
public class GestorLote {
    Session session;

    public LoteEntity getLoteByDenominacion (String denominacion){
        session = Conexion.getSessionFactory().openSession();
        java.util.List list;
        LoteEntity te = new LoteEntity();
        MomentoLaboreo mom;
        try {
            Query query = session.createQuery("select t from LoteEntity t where t.lteDenominacion = :pDenominacion and t.lteFechaBaja is null");
            query.setParameter("pDenominacion", denominacion);
            list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                te = (LoteEntity)iter.next();
            }
        }finally {
            session.close();}

        return te;
    }

    public LoteCampaniaEntity getLoteCampania (int idLote, int idCampania){
        session = Conexion.getSessionFactory().openSession();
        java.util.List list;
        LoteCampaniaEntity te = new LoteCampaniaEntity();
        MomentoLaboreo mom;
        try {
            Query query = session.createQuery("select t from LoteCampaniaEntity t where t.loteByLcpLteId = :pLteId and t.campaniaByLcpCnaId = :pCnaId and  t.lcpFechaBaja is null");
            query.setParameter("pLteId", idLote);
            query.setParameter("pCnaId", idCampania);
            list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                te = (LoteCampaniaEntity)iter.next();
            }
        }finally {
            session.close();}

        return te;
    }

}
