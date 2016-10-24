package Procesos;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OWNER on 7/3/2016.
 */
public class IngresoInsumo {

    private Integer nroSolicitud;
    private Date fechaOperacion;
    private int cantidadItems;

    private Integer ingresoId;
    private Integer ingresoNroRemito;
    private Date ingresoFecha;
    private Integer ingresoCantidadItems;

    private Date ingresoFechaAlta;
    private String ingresoUsuarioAlta;
    private Date ingresoFechaUltMod;
    private String ingresoUsuarioUltMod;
    private Date ingresoFechaBaja;
    private String ingresoUsuarioBaja;

    private ArrayList<DetalleIngresoInsumo> listaDetalleIngresoEntity;
    private List<Long> listaNroSolicitudes;


    public List<Long> getListaNroSolicitudes() {
        if(listaNroSolicitudes == null) listaNroSolicitudes = new ArrayList<>();
        return listaNroSolicitudes;
    }

    public void setListaNroSolicitudes(List<Long> listaNroSolicitudes) {
        this.listaNroSolicitudes = listaNroSolicitudes;
    }

    public Integer getIngresoId() {
        return ingresoId;
    }

    public void setIngresoId(Integer ingresoId) {
        this.ingresoId = ingresoId;
    }

    public Integer getIngresoNroRemito() {
        return ingresoNroRemito;
    }

    public void setIngresoNroRemito(Integer ingresoNroRemito) {
        this.ingresoNroRemito = ingresoNroRemito;
    }

    public Date getIngresoFecha() {
        return ingresoFecha;
    }

    public void setIngresoFecha(Date ingresoFecha) {
        this.ingresoFecha = ingresoFecha;
    }

    public Integer getIngresoCantidadItems() {
        return ingresoCantidadItems;
    }

    public void setIngresoCantidadItems(Integer ingresoCantidadItems) {
        this.ingresoCantidadItems = ingresoCantidadItems;
    }

    public Date getIngresoFechaAlta() {
        return ingresoFechaAlta;
    }

    public void setIngresoFechaAlta(Date ingresoFechaAlta) {
        this.ingresoFechaAlta = ingresoFechaAlta;
    }

    public String getIngresoUsuarioAlta() {
        return ingresoUsuarioAlta;
    }

    public void setIngresoUsuarioAlta(String ingresoUsuarioAlta) {
        this.ingresoUsuarioAlta = ingresoUsuarioAlta;
    }

    public Date getIngresoFechaUltMod() {
        return ingresoFechaUltMod;
    }

    public void setIngresoFechaUltMod(Date ingresoFechaUltMod) {
        this.ingresoFechaUltMod = ingresoFechaUltMod;
    }

    public String getIngresoUsuarioUltMod() {
        return ingresoUsuarioUltMod;
    }

    public void setIngresoUsuarioUltMod(String ingresoUsuarioUltMod) {
        this.ingresoUsuarioUltMod = ingresoUsuarioUltMod;
    }

    public Date getIngresoFechaBaja() {
        return ingresoFechaBaja;
    }

    public void setIngresoFechaBaja(Date ingresoFechaBaja) {
        this.ingresoFechaBaja = ingresoFechaBaja;
    }

    public String getIngresoUsuarioBaja() {
        return ingresoUsuarioBaja;
    }

    public void setIngresoUsuarioBaja(String ingresoUsuarioBaja) {
        this.ingresoUsuarioBaja = ingresoUsuarioBaja;
    }

    public ArrayList<DetalleIngresoInsumo> getListaDetalleIngresoEntity() {
        if(listaDetalleIngresoEntity == null) listaDetalleIngresoEntity = new ArrayList<>();
        return listaDetalleIngresoEntity;
    }

    public void setListaDetalleIngresoEntity(ArrayList<DetalleIngresoInsumo> listaDetalleIngresoEntity) {
        this.listaDetalleIngresoEntity = listaDetalleIngresoEntity;
    }

    public Integer getNroSolicitud() {
        return nroSolicitud;
    }

    public void setNroSolicitud(Integer nroSolicitud) {
        this.nroSolicitud = nroSolicitud;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }


    public int getCantidadItems() {
        return cantidadItems;
    }

    public void setCantidadItems(int cantidadItems) {
        this.cantidadItems = cantidadItems;
    }


}
