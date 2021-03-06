package Repository;

import Conexion.Conexion;
import Datos.TipoClienteEntity;
import Datos.TipoInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class TipoClienteRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<TipoClienteEntity> getAllTipoClientes(){
        List<TipoClienteEntity> listaTipoInsumo = new ArrayList<>();
        Session   session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        TipoClienteEntity tipoInsumo;
        Query query = session.createQuery("select x from TipoClienteEntity x");
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoInsumo = (TipoClienteEntity) iter.next();
            listaTipoInsumo.add(tipoInsumo);
        }
        tx.rollback();
        return listaTipoInsumo;
    }


    public TipoClienteEntity getTipoClienteByNombre(String nombre){
        Session    session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        TipoClienteEntity tipoInsumo = new TipoClienteEntity();
        Query query = session.createQuery("select x from TipoClienteEntity x where ucase(tinNombre) like ucase(:pNombre) and tinFechaBaja is null");
        query.setParameter("pNombre", nombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoInsumo = (TipoClienteEntity) iter.next();
        }
        tx.rollback();
        return tipoInsumo;
    }


    public TipoInsumoEntity getTipoInsumoById(Long id){
        Session   session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        TipoInsumoEntity tipoInsumo = new TipoInsumoEntity();
        Query query = session.createQuery("select x from TipoInsumoEntity x where ucase(tinId) like ucase(:pId) and tinFechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoInsumo = (TipoInsumoEntity) iter.next();
        }
        //session.close();
        return tipoInsumo;
    }

}
