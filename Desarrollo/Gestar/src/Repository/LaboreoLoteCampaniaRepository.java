package Repository;

import Conexion.Coneccion;
import Datos.InsumoEntity;
import Datos.LaboreoEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class LaboreoLoteCampaniaRepository {
    Session session = Coneccion.getSession();

    public List<LaboreoEntity> getAllLaboreoByLoteAndCampania(Integer nroLote, int camId){
        List<LaboreoEntity> listaLaboreo = new ArrayList<>();
        session = Coneccion.getSession();
        LaboreoEntity laboreo;
        Query query = session.createQuery("select x.laboreoByLlcLboId from LaboreoLoteCampaniaEntity x " +
                "where (x.loteCampaniaByLlcLcpId.loteByLcpLteId.id = :pId and x.loteCampaniaByLlcLcpId.campaniaByLcpCnaId.id = :pCamId)");
        query.setParameter("pId", nroLote);
        query.setParameter("pCamId", camId);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            laboreo = (LaboreoEntity) iter.next();
            listaLaboreo.add(laboreo);
        }
        session.close();
        return listaLaboreo;
    }


    public InsumoEntity getInsumoByNombre(String nombre){
         session = Coneccion.getSession();
        InsumoEntity insumo = new InsumoEntity();
        Query query = session.createQuery("select x from InsumoEntity x where ucase(insNombre) like ucase(:pNombre) and insFechaBaja is null");
        query.setParameter("pNombre", nombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (InsumoEntity) iter.next();
        }
        session.close();
        return insumo;
    }


    public InsumoEntity getInsumoById(Integer id){
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

}
