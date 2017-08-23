package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "ticket_pesada")
public class TicketPesadaEntity {

    private Integer id;
    private Integer nroTicket;
    private String  observaciones;
    private String  peso;
    private String  medida;


    private Date    fechaAlta;
    private String  usuarioAlta;
    private Date    fechaUltMod;
    private String  usuarioUltMod;
    private Date    fechaBaja;
    private String  usuarioBaja;

    private LoteCampaniaEntity loteCampania;
    private LaboreoEntity laboreo;
    private PlanificacionCampaniaEntity planificacion;
    private OrdenTrabajoEntity ordenTrabajoEntity;

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

    @Column(name = "nro_ticket")
    public Integer getNroTicket() {
        return nroTicket;
    }

    public void setNroTicket(Integer nroTicket) {
        this.nroTicket = nroTicket;
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
    @Column(name = "peso")
    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    @Basic
    @Column(name = "medida")
    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    @ManyToOne
    @JoinColumn(name = "orden_trabajo_id", referencedColumnName = "id")
    public OrdenTrabajoEntity getOrdenTrabajoEntity() {
        return ordenTrabajoEntity;
    }

    public void setOrdenTrabajoEntity(OrdenTrabajoEntity ordenTrabajoEntity) {
        this.ordenTrabajoEntity = ordenTrabajoEntity;
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
