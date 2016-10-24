package Procesos;

import Conexion.Coneccion;
import Datos.*;
import Lote.Lote;
import Repository.InsumoRepository;
import Repository.SolicitudInsumoRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by OWNER on 7/3/2016.
 */
public class GestorCompras {
    InsumoRepository insumoRepository = new InsumoRepository();
    SolicitudInsumoRepository solicitudInsumoRepository = new SolicitudInsumoRepository();

    public boolean registrarCompra(Compra solicitud){
        Session session = Coneccion.getSession();
        Transaction tx = session.beginTransaction();
        java.sql.Date time;
        java.util.Date utilDate = new java.util.Date();
        ArrayList <DetalleCompra> vectorDetalles = new ArrayList<>();
        ArrayList <DetalleSolicitudInsumoEntity> vectorDetallesEntity = new ArrayList<>();

        SolicitudInsumoEntity solicitudInsumoEntity = new SolicitudInsumoEntity();
        solicitudInsumoEntity.setSiNroSolicitud(solicitud.getNroSolicitud());
        solicitudInsumoEntity.setSiCantidadItems(solicitud.getCantidadItems());
//        solicitudInsumoEntity.setSiMontoTotal(solicitud.getMontoTotal());
        solicitudInsumoEntity.setSiFechaSolicitud(solicitud.getFechaOperacion());
        solicitudInsumoEntity.setSiFechaAlta(new java.sql.Date(utilDate.getTime()));
        solicitudInsumoEntity.setSiFechaUltMod(new java.sql.Date(utilDate.getTime()));
        solicitudInsumoEntity.setSiUsuarioAlta("Admin");
        solicitudInsumoEntity.setSiUsuarioUltMod("Admin");
        solicitudInsumoEntity.setSiEstado("En Curso");
        session.save(solicitudInsumoEntity);

        vectorDetalles = solicitud.getDetalles();
        for (int i = 0; i <vectorDetalles.size() ; i++) {

            DetalleSolicitudInsumoEntity detalleEntity = new DetalleSolicitudInsumoEntity();
            InsumoEntity insumoEntity = new InsumoEntity();
            insumoEntity = insumoRepository.getInsumoByNombre(vectorDetalles.get(i).getInsumo().getNombre());
            BigDecimal stock;
            if(insumoEntity.getInsStock() == null){
                stock = BigDecimal.valueOf(0);
            }else {
                stock = insumoEntity.getInsStock();
            }

//            insumoEntity.setInsStock(stock.add(vectorDetalles.get(i).getCantidad()));
            session.update(insumoEntity);

            detalleEntity.setInsumo(insumoEntity);
            detalleEntity.setDsiCantidad(vectorDetalles.get(i).getCantidad());
//            detalleEntity.setDsiPrecio(vectorDetalles.get(i).getPrecio());
            detalleEntity.setDsiObservaciones(vectorDetalles.get(i).getObservaciones());
            detalleEntity.setSolicitudInsumo(solicitudInsumoEntity);
            detalleEntity.setDsiFechaAlta(new java.sql.Date(utilDate.getTime()));
            detalleEntity.setDsiFechaUltMod(new java.sql.Date(utilDate.getTime()));
            detalleEntity.setDsiUsuarioAlta("Admin");
            detalleEntity.setDsiUsuarioUltMod("Admin");

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


    //INGRESO INSUMO REGISTRO REMITO
    public boolean registrarIngresoInsumos(IngresoInsumo ingreso){
        Session session = Coneccion.getSession();
        Transaction tx = session.beginTransaction();
        java.util.Date utilDate = new java.util.Date();
        ArrayList <DetalleIngresoInsumo> vectorDetalles = new ArrayList<>();
        ArrayList <DetalleIngresoInsumoEntity> vectorDetallesEntity = new ArrayList<>();

        IngresoInsumoEntity ingresoInsumoEntity = new IngresoInsumoEntity();
        ingresoInsumoEntity.setIngresoCantidadItems(ingreso.getCantidadItems());
        ingresoInsumoEntity.setIngresoFecha(ingreso.getFechaOperacion());
        ingresoInsumoEntity.setIngresoFechaAlta(new java.sql.Date(utilDate.getTime()));
        ingresoInsumoEntity.setIngresoFechaUltMod(new java.sql.Date(utilDate.getTime()));
        ingresoInsumoEntity.setIngresoUsuarioAlta("Admin");
        ingresoInsumoEntity.setIngresoUsuarioUltMod("Admin");
//        ingresoInsumoEntity.see("En Curso");

        for(Long nro: ingreso.getListaNroSolicitudes()){
            SolicitudInsumoEntity solicitud = solicitudInsumoRepository.getSolicitudInsumoById(nro);
            solicitud.setSiNroRemito(ingreso.getIngresoNroRemito());
            solicitud.setSiEstado("Finalizada");
            session.update(solicitud);
        }
        session.save(ingresoInsumoEntity);

        vectorDetalles = ingreso.getListaDetalleIngresoEntity();
        for (int i = 0; i <vectorDetalles.size() ; i++) {

            DetalleIngresoInsumoEntity detalleEntity = new DetalleIngresoInsumoEntity();
            InsumoEntity insumoEntity = new InsumoEntity();
            insumoEntity = insumoRepository.getInsumoByNombre(vectorDetalles.get(i).getInsumo().getNombre());
            BigDecimal stock;
            if(insumoEntity.getInsStock() == null){
                stock = BigDecimal.valueOf(0);
            }else {
                stock = insumoEntity.getInsStock();
            }

            insumoEntity.setInsStock(stock.add(BigDecimal.valueOf(vectorDetalles.get(i).getCantidadIngresada())));
            session.update(insumoEntity);

            detalleEntity.setInsumo(insumoEntity);
            detalleEntity.setDetalleIngresoCantidad(vectorDetalles.get(i).getDetalleIngresoCantidad());
//            detalleEntity.setCpdPrecio(vectorDetalles.get(i).getPrecio());
            detalleEntity.setDetalleIngresoObservaciones(vectorDetalles.get(i).getObservaciones());
            detalleEntity.setIngresoInsumo(ingresoInsumoEntity);
            detalleEntity.setDetalleIngresoFechaAlta(new java.sql.Date(utilDate.getTime()));
            detalleEntity.setDetalleIngresoFechaBaja(new java.sql.Date(utilDate.getTime()));
            detalleEntity.setDetalleIngresoUsuarioAlta("Admin");
            detalleEntity.setDetalleIngresoUsuarioUltMod("Admin");

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


    //GET SOLICITUDES
    public List getSolicitudesEnCurso() {
        Session session = Coneccion.getSession();
        java.util.List list;
        java.util.Collection col;
        LinkedList retorno = new LinkedList();
        SolicitudInsumoEntity solicitud = new SolicitudInsumoEntity();
        Lote lote;
        try {
            Query querySolicitud = session.createQuery("select t from SolicitudInsumoEntity t where siEstado = :pNombre");
            querySolicitud.setParameter("pNombre", "En Curso");
            list = querySolicitud.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                solicitud = (SolicitudInsumoEntity) iter.next();
                retorno.add(solicitud.getSiNroSolicitud());
            }

//                lote = new Lote(lcp.getLoteByLcpLteId().getLteDenominacion(), lcp.getLoteByLcpLteId().getLteCantMetros(), lcp.getLoteByLcpLteId().getLteUbicacion(), lcp.getLoteByLcpLteId().getLteFechaDesde(), lcp.getLoteByLcpLteId().getLteFechaHasta());
//                retorno.add(lote);

        } catch (Exception e) {
            System.out.print(e.toString());
        } finally {
            session.close();
        }

        return retorno;

    }
}
