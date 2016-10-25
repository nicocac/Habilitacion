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

    private Integer lboId;
    private Date lboFechaHoraInicio;
    private Date lboFechaHoraFin;
    private String lboDescripcion;
    private Date lboFechaAlta;
    private String lboUsuarioAlta;
    private Date lboFechaUltMod;
    private String lboUsuarioUltMod;
    private Date lboFechaBaja;
    private String lboUsuarioBaja;
//

    private TipoLaboreoEntity tipoLaboreoEntity;

    private Collection<DetalleLaboreoEntity> detalleLaboreosByLboId;
    private Collection<LaboreoLoteCampaniaEntity> laboreoLoteCampaniasByLboId;

    private TipoGranoEntity tipoGrano;
    //======================================================================================



    @Id
    @GeneratedValue
    @Column(name = "lbo_id")
    public Integer getLboId() {
        return lboId;
    }

    public void setLboId(Integer lboId) {
        this.lboId = lboId;
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

    @OneToMany(mappedBy = "laboreoByLlcLboId")
    public Collection<LaboreoLoteCampaniaEntity> getLaboreoLoteCampaniasByLboId() {
        return laboreoLoteCampaniasByLboId;
    }

    public void setLaboreoLoteCampaniasByLboId(Collection<LaboreoLoteCampaniaEntity> laboreoLoteCampaniasByLboId) {
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


    //=================================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LaboreoEntity that = (LaboreoEntity) o;

        if (lboId != that.lboId) return false;
        if (lboFechaHoraInicio != null ? !lboFechaHoraInicio.equals(that.lboFechaHoraInicio) : that.lboFechaHoraInicio != null)
            return false;
        if (lboFechaHoraFin != null ? !lboFechaHoraFin.equals(that.lboFechaHoraFin) : that.lboFechaHoraFin != null)
            return false;
        if (lboDescripcion != null ? !lboDescripcion.equals(that.lboDescripcion) : that.lboDescripcion != null)
            return false;
        if (lboFechaAlta != null ? !lboFechaAlta.equals(that.lboFechaAlta) : that.lboFechaAlta != null) return false;
        if (lboUsuarioAlta != null ? !lboUsuarioAlta.equals(that.lboUsuarioAlta) : that.lboUsuarioAlta != null)
            return false;
        if (lboFechaUltMod != null ? !lboFechaUltMod.equals(that.lboFechaUltMod) : that.lboFechaUltMod != null)
            return false;
        if (lboUsuarioUltMod != null ? !lboUsuarioUltMod.equals(that.lboUsuarioUltMod) : that.lboUsuarioUltMod != null)
            return false;
        if (lboFechaBaja != null ? !lboFechaBaja.equals(that.lboFechaBaja) : that.lboFechaBaja != null) return false;
        if (lboUsuarioBaja != null ? !lboUsuarioBaja.equals(that.lboUsuarioBaja) : that.lboUsuarioBaja != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lboId;
        result = 31 * result + (lboDescripcion != null ? lboDescripcion.hashCode() : 0);
        result = 31 * result + (lboFechaAlta != null ? lboFechaAlta.hashCode() : 0);
        result = 31 * result + (lboFechaBaja != null ? lboFechaBaja.hashCode() : 0);
        result = 31 * result + (lboFechaHoraFin != null ? lboFechaHoraFin.hashCode() : 0);
        result = 31 * result + (lboFechaHoraInicio != null ? lboFechaHoraInicio.hashCode() : 0);
        result = 31 * result + (lboFechaUltMod != null ? lboFechaUltMod.hashCode() : 0);
        result = 31 * result + (lboUsuarioAlta != null ? lboUsuarioAlta.hashCode() : 0);
        result = 31 * result + (lboUsuarioBaja != null ? lboUsuarioBaja.hashCode() : 0);
        result = 31 * result + (lboUsuarioUltMod != null ? lboUsuarioUltMod.hashCode() : 0);
        return result;
    }
}
