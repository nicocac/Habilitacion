package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "laboreo", schema = "", catalog = "gestar")
public class LaboreoEntity {

    private Long lboId;
    private String lboNombre;
    private Date lboFechaHoraInicio;
    private Date lboFechaHoraFin;
    private String lboDescripcion;
    private String metrica;
    private String medida;

    private Date lboFechaAlta;
    private String lboUsuarioAlta;
    private Date lboFechaUltMod;
    private String lboUsuarioUltMod;
    private Date lboFechaBaja;
    private String lboUsuarioBaja;
//

    private TipoLaboreoEntity tipoLaboreoEntity;

    private Collection<DetalleLaboreoEntity> detalleLaboreosByLboId;
    private Collection<PlanificacionCampaniaEntity> laboreoLoteCampaniasByLboId;

    private TipoGranoEntity tipoGrano;
    //======================================================================================



    @Id
    @GeneratedValue
    @Column(name = "lbo_id")
    public Long getLboId() {
        return lboId;
    }

    public void setLboId(Long lboId) {
        this.lboId = lboId;
    }


    @Basic
    @Column(name = "lbo_nombre")
    public String getLboNombre() {
        return lboNombre;
    }

    public void setLboNombre(String lboNombre) {
        this.lboNombre = lboNombre;
    }



    @Basic
    @Column(name = "lbo_fecha_hora_inicio")
    public Date getLboFechaHoraInicio() {
        return lboFechaHoraInicio;
    }

    public void setLboFechaHoraInicio(Date lboFechaHoraInicio) {
        this.lboFechaHoraInicio = lboFechaHoraInicio;
    }

    @Basic
    @Column(name = "lbo_fecha_hora_fin")
    public Date getLboFechaHoraFin() {
        return lboFechaHoraFin;
    }

    public void setLboFechaHoraFin(Date lboFechaHoraFin) {
        this.lboFechaHoraFin = lboFechaHoraFin;
    }

    @Basic
    @Column(name = "lbo_descripcion")
    public String getLboDescripcion() {
        return lboDescripcion;
    }

    public void setLboDescripcion(String lboDescripcion) {
        this.lboDescripcion = lboDescripcion;
    }


    @Basic
    @Column(name = "lbo_metrica")
    public String getMetrica() {
        return metrica;
    }

    public void setMetrica(String metrica) {
        this.metrica = metrica;
    }

    @Basic
    @Column(name = "lbo_medida")
    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    @Basic
    @Column(name = "lbo_fecha_alta")
    public Date getLboFechaAlta() {
        return lboFechaAlta;
    }

    public void setLboFechaAlta(Date lboFechaAlta) {
        this.lboFechaAlta = lboFechaAlta;
    }

    @Basic
    @Column(name = "lbo_usuario_alta")
    public String getLboUsuarioAlta() {
        return lboUsuarioAlta;
    }

    public void setLboUsuarioAlta(String lboUsuarioAlta) {
        this.lboUsuarioAlta = lboUsuarioAlta;
    }

    @Basic
    @Column(name = "lbo_fecha_ult_mod")
    public Date getLboFechaUltMod() {
        return lboFechaUltMod;
    }

    public void setLboFechaUltMod(Date lboFechaUltMod) {
        this.lboFechaUltMod = lboFechaUltMod;
    }

    @Basic
    @Column(name = "lbo_usuario_ult_mod")
    public String getLboUsuarioUltMod() {
        return lboUsuarioUltMod;
    }

    public void setLboUsuarioUltMod(String lboUsuarioUltMod) {
        this.lboUsuarioUltMod = lboUsuarioUltMod;
    }

    @Basic
    @Column(name = "lbo_fecha_baja")
    public Date getLboFechaBaja() {
        return lboFechaBaja;
    }

    public void setLboFechaBaja(Date lboFechaBaja) {
        this.lboFechaBaja = lboFechaBaja;
    }

    @Basic
    @Column(name = "lbo_usuario_baja")
    public String getLboUsuarioBaja() {
        return lboUsuarioBaja;
    }

    public void setLboUsuarioBaja(String lboUsuarioBaja) {
        this.lboUsuarioBaja = lboUsuarioBaja;
    }

    @OneToMany(mappedBy = "laboreoByDboLboId")
    public Collection<DetalleLaboreoEntity> getDetalleLaboreosByLboId() {
        return detalleLaboreosByLboId;
    }

    public void setDetalleLaboreosByLboId(Collection<DetalleLaboreoEntity> detalleLaboreosByLboId) {
        this.detalleLaboreosByLboId = detalleLaboreosByLboId;
    }

    @OneToMany(mappedBy = "laboreo")
    public Collection<PlanificacionCampaniaEntity> getLaboreoLoteCampaniasByLboId() {
        return laboreoLoteCampaniasByLboId;
    }

    public void setLaboreoLoteCampaniasByLboId(Collection<PlanificacionCampaniaEntity> laboreoLoteCampaniasByLboId) {
        this.laboreoLoteCampaniasByLboId = laboreoLoteCampaniasByLboId;
    }

    @ManyToOne
    @JoinColumn(name = "lbo_tlbo_id", referencedColumnName = "tpo_id")
    public TipoLaboreoEntity getTipoLaboreoEntity() {
        return tipoLaboreoEntity;
    }

    public void setTipoLaboreoEntity(TipoLaboreoEntity tipoLaboreoEntity) {
        this.tipoLaboreoEntity = tipoLaboreoEntity;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_grano_id", referencedColumnName = "tgr_id")
    public TipoGranoEntity getTipoGrano() {
        return tipoGrano;
    }

    public void setTipoGrano(TipoGranoEntity tipoGrano) {
        this.tipoGrano = tipoGrano;
    }

    //=================================================================


    @Override
    public String toString() {
        return
                lboNombre;
    }
}
