package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "campania", schema = "", catalog = "gestar")
public class OrdenTrabajoEntity {

    private Integer id;
    private String  observaciones;
    private String  recursoHumano;
    private String  tiempo;


    private Date    fechaAlta;
    private String  usuarioAlta;
    private Date    fechaUltMod;
    private String  usuarioUltMod;
    private Date    fechaBaja;
    private String  usuarioBaja;

    private LoteCampaniaEntity loteCampania;
    private LaboreoEntity laboreo;
    private PlanificacionCampaniaEntity planificacion;

    //=================================================================
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
    @Column(name = "recurso_humano")
    public String getRecursoHumano() {
        return recursoHumano;
    }

    public void setRecursoHumano(String recursoHumano) {
        this.recursoHumano = recursoHumano;
    }

    @Basic
    @Column(name = "tiempo")
    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
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
    @JoinColumn(name = "laboreo_id", referencedColumnName = "lbo_id")
    public LaboreoEntity getLaboreo() {
        return laboreo;
    }

    public void setLaboreo(LaboreoEntity laboreo) {
        this.laboreo = laboreo;
    }

    @ManyToOne
    @JoinColumn(name = "planificacion_id", referencedColumnName = "planificacion_id")
    public PlanificacionCampaniaEntity getPlanificacion() {
        return planificacion;
    }

    public void setPlanificacion(PlanificacionCampaniaEntity planificacion) {
        this.planificacion = planificacion;
    }



}
