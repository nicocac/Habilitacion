package Repository;

import Conexion.Conexion;
import Datos.ClienteEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class ClienteRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<ClienteEntity> getAllClientes(){
        List<ClienteEntity> listaInsumo = new ArrayList<>();
        Session session = Conexion.getSessionFactory().openSession();
        ClienteEntity insumo;
        Query query = session.createQuery("select x from ClienteEntity x");
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (ClienteEntity) iter.next();
            listaInsumo.add(insumo);
        }
        session.close();
        return listaInsumo;
    }


    public ClienteEntity getClienteByNombre(String nombre){
        Session session = Conexion.getSessionFactory().openSession();
        ClienteEntity insumo = new ClienteEntity();
        Query query = session.createQuery("select x from ClienteEntity x where ucase(clienteNombre) like ucase(:pNombre) and clienteFechaBaja is null");
        query.setParameter("pNombre", nombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (ClienteEntity) iter.next();
        }
        session.close();
        return insumo;
    }


    public ClienteEntity getClienteById(Long id){
        Session session = Conexion.getSessionFactory().openSession();
        ClienteEntity insumo = new ClienteEntity();
        Query query = session.createQuery("select x from ClienteEntity x where ucase(clienteNombre) like ucase(:pId) and clienteFechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (ClienteEntity) iter.next();
        }
        session.close();
        return insumo;
    }

}