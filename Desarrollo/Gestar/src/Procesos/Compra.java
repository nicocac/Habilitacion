package Procesos;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by OWNER on 7/3/2016.
 */
public class Compra {
    private Date fechaOperacion;
    private BigDecimal montoTotal;
    private int cantidadItems;
    private ArrayList<DetalleCompra> detalles;

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getCantidadItems() {
        return cantidadItems;
    }

    public void setCantidadItems(int cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

    public ArrayList<DetalleCompra> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleCompra> detalles) {
        this.detalles = detalles;
    }
}
