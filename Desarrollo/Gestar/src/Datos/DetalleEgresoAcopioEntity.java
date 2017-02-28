package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 7/3/2016.
 */
@Entity
@Table(name = "detalle_egreso_acopio", schema = "", catalog = "gestar")
public class DetalleEgresoAcopioEntity {


    private Integer detalleEgresoId;
    private Long detalleEgresoCantidad;
    private Double detalleEgresoPrecio;
    private String detalleEgresoObservaciones;

    private Date detalleEgresoFechaAlta;
    private String detalleEgresoUsuarioAlta;
    private Date detalleEgresoFechaUltMod;
    private String detalleEgresoUsuarioUltMod;
    private Date detalleEgresoFechaBaja;
    private String detalleEgresoUsuarioBaja;

    private EgresoAcopioEntity egresoAcopio;
    private TipoGranoEntity tipoGrano;
    private AcopioEntity acopio;




    //=================================================================

    @Id
    @GeneratedValue
    @Column(name = "detalle_egreso_id")

    public Integer getDetalleEgresoId() {
        return detalleEgresoId;
    }

    public void setDetalleEgresoId(Integer detalleEgresoId) {
        this.detalleEgresoId = detalleEgresoId;
    }

    @Column(name = "detalle_egreso_cantidad")
    public Long getDetalleEgresoCantidad() {
        return detalleEgresoCantidad;
    }

    public void setDetalleEgresoCantidad(Long detalleEgresoCantidad) {
        this.detalleEgresoCantidad = detalleEgresoCantidad;
    }

    @Column(name = "detalle_egreso_precio")
    public Double getDetalleEgresoPrecio() {
        return detalleEgresoPrecio;
    }

    public void setDetalleEgresoPrecio(Double detalleEgresoPrecio) {
        this.detalleEgresoPrecio = detalleEgresoPrecio;
    }
    @Column(name = "detalle_egreso_observaciones")
    public String getDetalleEgresoObservaciones() {
        return detalleEgresoObservaciones;
    }

    public void setDetalleEgresoObservaciones(String detalleEgresoObservaciones) {
        this.detalleEgresoObservaciones = detalleEgresoObservaciones;
    }


    @Column(name = "detalle_egreso_fecha_alta")
    public Date getDetalleEgresoFechaAlta() {
        return detalleEgresoFechaAlta;
    }

    public void setDetalleEgresoFechaAlta(Date detalleEgresoFechaAlta) {
        this.detalleEgresoFechaAlta = detalleEgresoFechaAlta;
    }
    @Column(name = "detalle_egreso_usuario_alta")

    public String getDetalleEgresoUsuarioAlta() {
        return detalleEgresoUsuarioAlta;
    }

    public void setDetalleEgresoUsuarioAlta(String detalleEgresoUsuarioAlta) {
        this.detalleEgresoUsuarioAlta = detalleEgresoUsuarioAlta;
    }
    @Column(name = "detalle_egreso_fecha_ult_mod")

    public Date getDetalleEgresoFechaUltMod() {
        return detalleEgresoFechaUltMod;
    }

    public void setDetalleEgresoFechaUltMod(Date detalleEgresoFechaUltMod) {
        this.detalleEgresoFechaUltMod = detalleEgresoFechaUltMod;
    }
    @Column(name = "detalle_egreso_usuario_utl_mod")

    public String getDetalleEgresoUsuarioUltMod() {
        return detalleEgresoUsuarioUltMod;
    }

    public void setDetalleEgresoUsuarioUltMod(String detalleEgresoUsuarioUltMod) {
        this.detalleEgresoUsuarioUltMod = detalleEgresoUsuarioUltMod;
    }
    @Column(name = "detalle_egreso_fecha_baja")

    public Date getDetalleEgresoFechaBaja() {
        return detalleEgresoFechaBaja;
    }

    public void setDetalleEgresoFechaBaja(Date detalleEgresoFechaBaja) {
        this.detalleEgresoFechaBaja = detalleEgresoFechaBaja;
    }
    @Column(name = "detalle_egreso_usuario_baja")

    public String getDetalleEgresoUsuarioBaja() {
        return detalleEgresoUsuarioBaja;
    }

    public void setDetalleEgresoUsuarioBaja(String detalleEgresoUsuarioBaja) {
        this.detalleEgresoUsuarioBaja = detalleEgresoUsuarioBaja;
    }
    @ManyToOne
    @JoinColumn(name = "egreso_acopio_id", referencedColumnName = "egreso_id")
    public EgresoAcopioEntity getEgresoAcopio() {
        return egresoAcopio;
    }

    public void setEgresoAcopio(EgresoAcopioEntity egresoAcopio) {
        this.egresoAcopio = egresoAcopio;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_grano_id", referencedColumnName = "tgr_id")
    public TipoGranoEntity getTipoGrano() {
        return tipoGrano;
    }

    public void setTipoGrano(TipoGranoEntity tipoGrano) {
        this.tipoGrano = tipoGrano;
    }


    @ManyToOne
    @JoinColumn(name = "acopio_id", referencedColumnName = "acopio_id")
    public AcopioEntity getAcopio() {
        return acopio;
    }

    public void setAcopio(AcopioEntity acopio) {
        this.acopio = acopio;
    }
}
