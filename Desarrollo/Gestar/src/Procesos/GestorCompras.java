package Procesos;

import Conexion.Coneccion;
import Datos.CompraEntity;
import Datos.DetalleCompraEntity;
import Datos.InsumoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by OWNER on 7/3/2016.
 */
public class GestorCompras {
    public boolean registrarCompra(Compra compra){
        Session session = Coneccion.getSession();
        Transaction tx = session.beginTransaction();
        ArrayList <DetalleCompra> vectorDetalles = new ArrayList<DetalleCompra>();
        ArrayList <DetalleCompraEntity> vectorDetallesEntity = new ArrayList<DetalleCompraEntity>();
        CompraEntity compraEntity = new CompraEntity();
        compraEntity.setCpaCantidadItems(compra.getCantidadItems());
        compraEntity.setCpaMontoTotal(compra.getMontoTotal());
        compraEntity.setCpaFechaCompra(compra.getFechaOperacion());
        java.sql.Date time;
        java.util.Date utilDate = new java.util.Date();
        compraEntity.setCpaFechaAlta(new java.sql.Date(utilDate.getTime()));
        compraEntity.setCpaFechaUltMod(new java.sql.Date(utilDate.getTime()));
        compraEntity.setCpaUsuarioAlta("Admin");
        compraEntity.setCpaUsuarioUltMod("Admin");
        session.save(compraEntity);
        vectorDetalles = compra.getDetalles();
        for (int i = 0; i <vectorDetalles.size() ; i++) {
            DetalleCompraEntity detalleEntity = new DetalleCompraEntity();
            InsumoEntity ie = new InsumoEntity();
            ie = ie.getByNombre(vectorDetalles.get(i).getInsumo().getNombre());
            ie.setInsStock(ie.getInsStock().add(vectorDetalles.get(i).getCantidad()));
            session.update(ie);
             detalleEntity.setInsumoByCpdInsId(ie);
            detalleEntity.setCpdCantidad(vectorDetalles.get(i).getCantidad());
            detalleEntity.setCpdPrecio(vectorDetalles.get(i).getPrecio());
            detalleEntity.setCpdObservaciones(vectorDetalles.get(i).getObservaciones());
            detalleEntity.setCompraByCpdCpaId(compraEntity);
            detalleEntity.setCpdFechaAlta(new java.sql.Date(utilDate.getTime()));
            detalleEntity.setCpdFechaUltMod(new java.sql.Date(utilDate.getTime()));
            detalleEntity.setCpdUsuarioAlta("Admin");
            detalleEntity.setCpdUsuarioUltMod("Admin");
            session.save(detalleEntity);
            vectorDetallesEntity.add(detalleEntity);

        }
        try {
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
        }
        session.close();
        return tx.wasCommitted();
    }
}
