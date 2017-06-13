package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 9/4/2016.
 */
@Entity
@Table(name = "planificacion_campania")
public class PlanificacionCampaniaEntity {

    private Integer planificacionId;
    private String observaciones;

    private Date fechaAlta;
    private String usuarioAlta;
    private Date fechaUltMod;
    private String usuarioUltMod;
    private Date fechaBaja;
    private String usuarioBaja;

    private LoteCampaniaEntity loteCampania;
    private CampaniaEntity campania;

//////////////



    //======================================================================================

    @Id
    @GeneratedValue
    @Column(name = "planificacion_id")
    public Integer getPlanificacionId() {
        return planificacionId;
    }

    public void setPlanificacionId(Integer planificacionId) {
        this.planificacionId = planificacionId;
    }

    @Column(name = "observaciones")
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


    @Column(name = "fecha_alta")
    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Column(name = "usuario_alta")
    public String getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    @Column(name = "fecha_ult_mod")
    public Date getFechaUltMod() {
        return fechaUltMod;
    }

    public void setFechaUltMod(Date fechaUltMod) {
        this.fechaUltMod = fechaUltMod;
    }

    @Column(name = "usuario_ult_mod")
    public String getUsuarioUltMod() {
        return usuarioUltMod;
    }

    public void setUsuarioUltMod(String usuarioUltMod) {
        this.usuarioUltMod = usuarioUltMod;
    }

    @Column(name = "fecha_baja")
    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

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
    @JoinColumn(name = "campania_id", referencedColumnName = "cna_id")

    public CampaniaEntity getCampania() {
        return campania;
    }

    public void setCampania(CampaniaEntity campania) {
        this.campania = campania;
    }
}
