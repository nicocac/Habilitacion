package Procesos;

import Conexion.Conexion;
import Datos.InsumoEntity;
import Insumo.Insumo;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Date;

/**
 * Created by OWNER on 7/3/2016.
 */
public class DetalleIngresoInsumo {
    private Insumo insumo;
    private Long cantidadSolicitada;
    private Long cantidadIngresada;
    private String observaciones;

    private Integer detalleIngresoId;
    private Long detalleIngresoCantidad;

    private Date detalleIngresoFechaAlta;
    private String detalleIngresoUsuarioAlta;
    private Date detalleIngresoFechaUltMod;
    private String detalleIngresoUsuarioUltMod;
    private Date detalleIngresoFechaBaja;
    private String detalleIngresoUsuarioBaja;

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }


    public Long getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(Long cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Long getCantidadIngresada() {
        return cantidadIngresada;
    }

    public void setCantidadIngresada(Long cantidadIngresada) {
        this.cantidadIngresada = cantidadIngresada;
    }

    public Integer getDetalleIngresoId() {
        return detalleIngresoId;
    }

    public void setDetalleIngresoId(Integer detalleIngresoId) {
        this.detalleIngresoId = detalleIngresoId;
    }

    public Long getDetalleIngresoCantidad() {
        return detalleIngresoCantidad;
    }

    public void setDetalleIngresoCantidad(Long detalleIngresoCantidad) {
        this.detalleIngresoCantidad = detalleIngresoCantidad;
    }

    public Date getDetalleIngresoFechaAlta() {
        return detalleIngresoFechaAlta;
    }

    public void setDetalleIngresoFechaAlta(Date detalleIngresoFechaAlta) {
        this.detalleIngresoFechaAlta = detalleIngresoFechaAlta;
    }

    public String getDetalleIngresoUsuarioAlta() {
        return detalleIngresoUsuarioAlta;
    }

    public void setDetalleIngresoUsuarioAlta(String detalleIngresoUsuarioAlta) {
        this.detalleIngresoUsuarioAlta = detalleIngresoUsuarioAlta;
    }

    public Date getDetalleIngresoFechaUltMod() {
        return detalleIngresoFechaUltMod;
    }

    public void setDetalleIngresoFechaUltMod(Date detalleIngresoFechaUltMod) {
        this.detalleIngresoFechaUltMod = detalleIngresoFechaUltMod;
    }

    public String getDetalleIngresoUsuarioUltMod() {
        return detalleIngresoUsuarioUltMod;
    }

    public void setDetalleIngresoUsuarioUltMod(String detalleIngresoUsuarioUltMod) {
        this.detalleIngresoUsuarioUltMod = detalleIngresoUsuarioUltMod;
    }

    public Date getDetalleIngresoFechaBaja() {
        return detalleIngresoFechaBaja;
    }

    public void setDetalleIngresoFechaBaja(Date detalleIngresoFechaBaja) {
        this.detalleIngresoFechaBaja = detalleIngresoFechaBaja;
    }

    public String getDetalleIngresoUsuarioBaja() {
        return detalleIngresoUsuarioBaja;
    }

    public void setDetalleIngresoUsuarioBaja(String detalleIngresoUsuarioBaja) {
        this.detalleIngresoUsuarioBaja = detalleIngresoUsuarioBaja;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getLastId(){
        Session session = Conexion.getSessionFactory().openSession();
        int i = 0;
        int max = 0;
        try {
            InsumoEntity insumo = new InsumoEntity();
            Query query = session.createQuery("select max(t.cpdId) from DetalleSolicitudInsumoEntity t ");
            java.util.List list = query.list();
            for (i = 0; i < list.size(); i++) {
                max = (Integer) list.get(i);
            }
            return max;
        }catch (Exception e){
            return -1;
        }finally {
            session.close();
        }
    }
}
