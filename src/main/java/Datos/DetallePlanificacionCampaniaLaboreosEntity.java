package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "detalle_planificacion_campania_laboreos", schema = "", catalog = "gestar")
public class DetallePlanificacionCampaniaLaboreosEntity {


    private Integer id;
    private String observaciones;

    private Date fechaAlta;
    private String usuarioAlta;
    private Date fechaUltMod;
    private String usuarioUltMod;
    private Date fechaBaja;
    private String usuarioBaja;

    private LaboreoEntity laboreo;
    private  DetallePlanificacionCampaniaLoteEntity detallePlanificacionCampaniaLote;
    private Boolean tieneOrdenTrabajo;
    private TipoGranoEntity tipoGrano;

    ////////////////////

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "observaciones")
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Basic
    @Column(name = "fecha_alta")
    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Basic
    @Column(name = "usuario_alta")
    public String getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    @Basic
    @Column(name = "fecha_ult_mod")
    public Date getFechaUltMod() {
        return fechaUltMod;
    }

    public void setFechaUltMod(Date fechaUltMod) {
        this.fechaUltMod = fechaUltMod;
    }

    @Basic
    @Column(name = "usuario_ult_mod")
    public String getUsuarioUltMod() {
        return usuarioUltMod;
    }

    public void setUsuarioUltMod(String usuarioUltMod) {
        this.usuarioUltMod = usuarioUltMod;
    }

    @Basic
    @Column(name = "fecha_baja")
    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @Basic
    @Column(name = "usuario_baja")
    public String getUsuarioBaja() {
        return usuarioBaja;
    }

    public void setUsuarioBaja(String usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }


    @ManyToOne
    @JoinColumn(name = "laboreo_id", referencedColumnName = "lbo_id", nullable = false)
    public LaboreoEntity getLaboreo() {
        return laboreo;
    }

    public void setLaboreo(LaboreoEntity laboreo) {
        this.laboreo = laboreo;
    }


    @ManyToOne
    @JoinColumn(name = "tipo_grano_id", referencedColumnName = "tgr_id")
    public TipoGranoEntity getTipoGrano() {
        return tipoGrano;
    }

    public void setTipoGrano(TipoGranoEntity tipoGrano) {
        this.tipoGrano = tipoGrano;
    }


    @Column(name = "tiene_orden")
    public Boolean getTieneOrdenTrabajo() {
        return tieneOrdenTrabajo;
    }

    public void setTieneOrdenTrabajo(Boolean tieneOrdenTrabajo) {
        this.tieneOrdenTrabajo = tieneOrdenTrabajo;
    }


    @ManyToOne
    @JoinColumn(name = "detalle_planificacion_campania_lote_id", referencedColumnName = "id")
    public DetallePlanificacionCampaniaLoteEntity getDetallePlanificacionCampaniaLote() {
        return detallePlanificacionCampaniaLote;
    }

    public void setDetallePlanificacionCampaniaLote(DetallePlanificacionCampaniaLoteEntity detallePlanificacionCampaniaLote) {
        this.detallePlanificacionCampaniaLote = detallePlanificacionCampaniaLote;
    }

    //=================================================================


}
