package Procesos;

import Conexion.Coneccion;
import Datos.InsumoEntity;
import Insumo.Insumo;
import org.hibernate.Query;
import org.hibernate.Session;

import java.math.BigDecimal;

/**
 * Created by OWNER on 7/3/2016.
 */
public class DetalleCompra {
    private Insumo insumo;
    private BigDecimal cantidad;
    private BigDecimal precio;
    private String observaciones;

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getLastId(){
        Session session = Coneccion.getSession();
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
