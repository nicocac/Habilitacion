package Datos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by OWNER on 7/3/2016.
 */
@Entity
@Table(name = "detalle_ingreso", schema = "", catalog = "gestar")
public class DetalleIngresoInsumoEntity {


    private Integer detalleIngresoId;
    private Long detalleIngresoCantidad;
    private Double detalleIngresoPrecio;
    private String detalleIngresoObservaciones;

    private Date detalleIngresoFechaAlta;
    private String detalleIngresoUsuarioAlta;
    private Date detalleIngresoFechaUltMod;
    private String detalleIngresoUsuarioUltMod;
    private Date detalleIngresoFechaBaja;
    private String detalleIngresoUsuarioBaja;

    private IngresoInsumoEntity ingresoInsumo;
    private InsumoEntity insumo;

    //======================================================================================

    //=================================================================

    @Id
    @GeneratedValue
    @Column(name = "detalle_ingreso_id")
    public Integer getDetalleIngresoId() {
        return detalleIngresoId;
    }

    public void setDetalleIngresoId(Integer detalleIngresoId) {
        this.detalleIngresoId = detalleIngresoId;
    }

    @Column(name = "detalle_ingreso_cantidad")
    public Long getDetalleIngresoCantidad() {
        return detalleIngresoCantidad;
    }

    public void setDetalleIngresoCantidad(Long detalleIngresoCantidad) {
        this.detalleIngresoCantidad = detalleIngresoCantidad;
    }

    @Column(name = "detalle_ingreso_precio")
    public Double getDetalleIngresoPrecio() {
        return detalleIngresoPrecio;
    }

    public void setDetalleIngresoPrecio(Double detalleIngresoPrecio) {
        this.detalleIngresoPrecio = detalleIngresoPrecio;
    }

    @Column(name = "detalle_ingreso_observaciones")
    public String getDetalleIngresoObservaciones() {
        return detalleIngresoObservaciones;
    }

    public void setDetalleIngresoObservaciones(String detalleIngresoObservaciones) {
        this.detalleIngresoObservaciones = detalleIngresoObservaciones;
    }

    @Column(name = "detalle_ingreso_fecha_alta")
    public Date getDetalleIngresoFechaAlta() {
        return detalleIngresoFechaAlta;
    }

    public void setDetalleIngresoFechaAlta(Date detalleIngresoFechaAlta) {
        this.detalleIngresoFechaAlta = detalleIngresoFechaAlta;
    }

    @Column(name = "detalle_ingreso_usuario_alta")
    public String getDetalleIngresoUsuarioAlta() {
        return detalleIngresoUsuarioAlta;
    }

    public void setDetalleIngresoUsuarioAlta(String detalleIngresoUsuarioAlta) {
        this.detalleIngresoUsuarioAlta = detalleIngresoUsuarioAlta;
    }

    @Column(name = "detalle_ingreso_fecha_ult_mod")
    public Date getDetalleIngresoFechaUltMod() {
        return detalleIngresoFechaUltMod;
    }

    public void setDetalleIngresoFechaUltMod(Date detalleIngresoFechaUltMod) {
        this.detalleIngresoFechaUltMod = detalleIngresoFechaUltMod;
    }

    @Column(name = "detalle_ingreso_usuario_ult_mod")
    public String getDetalleIngresoUsuarioUltMod() {
        return detalleIngresoUsuarioUltMod;
    }

    public void setDetalleIngresoUsuarioUltMod(String detalleIngresoUsuarioUltMod) {
        this.detalleIngresoUsuarioUltMod = detalleIngresoUsuarioUltMod;
    }

    @Column(name = "detalle_ingreso_fecha_baja")
    public Date getDetalleIngresoFechaBaja() {
        return detalleIngresoFechaBaja;
    }

    public void setDetalleIngresoFechaBaja(Date detalleIngresoFechaBaja) {
        this.detalleIngresoFechaBaja = detalleIngresoFechaBaja;
    }

    @Column(name = "detalle_ingreso_usuario_baja")
    public String getDetalleIngresoUsuarioBaja() {
        return detalleIngresoUsuarioBaja;
    }

    public void setDetalleIngresoUsuarioBaja(String detalleIngresoUsuarioBaja) {
        this.detalleIngresoUsuarioBaja = detalleIngresoUsuarioBaja;
    }

    @ManyToOne
    @JoinColumn(name = "ingreso_insumo_id", referencedColumnName = "ingreso_id")
    public IngresoInsumoEntity getIngresoInsumo() {
        return ingresoInsumo;
    }

    public void setIngresoInsumo(IngresoInsumoEntity ingresoInsumo) {
        this.ingresoInsumo = ingresoInsumo;
    }

    @ManyToOne
    @JoinColumn(name = "insumo_id", referencedColumnName = "ins_id")
    public InsumoEntity getInsumo() {
        return insumo;
    }

    public void setInsumo(InsumoEntity insumo) {
        this.insumo = insumo;
    }




}
