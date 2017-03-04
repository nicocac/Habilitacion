package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "detalle_planificacion_campania_lotes", schema = "", catalog = "gestar")
public class DetallePlanificacionCampaniaLoteEntity {


    private Integer id;
    private String observaciones;

    private Date fechaAlta;
    private String usuarioAlta;
    private Date fechaUltMod;
    private String usuarioUltMod;
    private Date fechaBaja;
    private String usuarioBaja;

    private PlanificacionCampaniaEntity planificacion;
    private LoteEntity lote;

    ////////////////////
    private LoteCampaniaEntity loteCampania;

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
    @JoinColumn(name = "lote_campania_id", referencedColumnName = "lcp_id")
    public LoteCampaniaEntity getLoteCampania() {
        return loteCampania;
    }

    public void setLoteCampania(LoteCampaniaEntity loteCampania) {
        this.loteCampania = loteCampania;
    }


    @ManyToOne
    @JoinColumn(name = "planificacion_campania_id", referencedColumnName = "planificacion_id")
    public PlanificacionCampaniaEntity getPlanificacion() {
        return planificacion;
    }

    public void setPlanificacion(PlanificacionCampaniaEntity planificacion) {
        this.planificacion = planificacion;
    }

    @ManyToOne
    @JoinColumn(name = "lote_id", referencedColumnName = "lte_id")
    public LoteEntity getLote() {
        return lote;
    }

    public void setLote(LoteEntity lote) {
        this.lote = lote;
    }

    //=================================================================


}
