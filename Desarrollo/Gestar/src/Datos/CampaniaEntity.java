package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "campania", schema = "", catalog = "gestar")
public class CampaniaEntity {
    private Integer cnaId;
    private String cnaDenominacion;
    private Date cnaFechaInicio;
    private Date cnaFechaFinEstimada;
    private Date cnaFechaFinReal;
    private Date cnaFechaAlta;
    private String cnaUsuarioAlta;
    private Date cnaFechaUltMod;
    private String cnaUsuarioUltMod;
    private Date cnaFechaBaja;
    private String cnaUsuarioBaja;

    private Collection<LoteCampaniaEntity> loteCampaniasByCnaId;

    //=================================================================
    @Id
    @GeneratedValue
    @Column(name = "cna_id")
    public Integer getCnaId() {
        return cnaId;
    }
    public void setCnaId(Integer cnaId) {
        this.cnaId = cnaId;
    }

    @Basic
    @Column(name = "cna_denominacion")
    public String getCnaDenominacion() {
        return cnaDenominacion;
    }
    public void setCnaDenominacion(String cnaDenominacion) {
        this.cnaDenominacion = cnaDenominacion;
    }

    @Basic
    @Column(name = "cna_fecha_inicio")
    public Date getCnaFechaInicio() {
        return cnaFechaInicio;
    }

    public void setCnaFechaInicio(Date cnaFechaInicio) {
        this.cnaFechaInicio = cnaFechaInicio;
    }

    @Basic
    @Column(name = "cna_fecha_fin_estimada")
    public Date getCnaFechaFinEstimada() {
        return cnaFechaFinEstimada;
    }

    public void setCnaFechaFinEstimada(Date cnaFechaFinEstimada) {
        this.cnaFechaFinEstimada = cnaFechaFinEstimada;
    }

    @Basic
    @Column(name = "cna_fecha_fin_real")
    public Date getCnaFechaFinReal() {
        return cnaFechaFinReal;
    }

    public void setCnaFechaFinReal(Date cnaFechaFinReal) {
        this.cnaFechaFinReal = cnaFechaFinReal;
    }

    @Basic
    @Column(name = "cna_fecha_alta")
    public Date getCnaFechaAlta() {
        return cnaFechaAlta;
    }

    public void setCnaFechaAlta(Date cnaFechaAlta) {
        this.cnaFechaAlta = cnaFechaAlta;
    }

    @Basic
    @Column(name = "cna_usuario_alta")
    public String getCnaUsuarioAlta() {
        return cnaUsuarioAlta;
    }

    public void setCnaUsuarioAlta(String cnaUsuarioAlta) {
        this.cnaUsuarioAlta = cnaUsuarioAlta;
    }

    @Basic
    @Column(name = "cna_fecha_ult_mod")
    public Date getCnaFechaUltMod() {
        return cnaFechaUltMod;
    }

    public void setCnaFechaUltMod(Date cnaFechaUltMod) {
        this.cnaFechaUltMod = cnaFechaUltMod;
    }

    @Basic
    @Column(name = "cna_usuario_ult_mod")
    public String getCnaUsuarioUltMod() {
        return cnaUsuarioUltMod;
    }

    public void setCnaUsuarioUltMod(String cnaUsuarioUltMod) {
        this.cnaUsuarioUltMod = cnaUsuarioUltMod;
    }

    @Basic
    @Column(name = "cna_fecha_baja")
    public Date getCnaFechaBaja() {
        return cnaFechaBaja;
    }

    public void setCnaFechaBaja(Date cnaFechaBaja) {
        this.cnaFechaBaja = cnaFechaBaja;
    }

    @Basic
    @Column(name = "cna_usuario_baja")
    public String getCnaUsuarioBaja() {
        return cnaUsuarioBaja;
    }

    public void setCnaUsuarioBaja(String cnaUsuarioBaja) {
        this.cnaUsuarioBaja = cnaUsuarioBaja;
    }


    @OneToMany(mappedBy = "campaniaByLcpCnaId")
    public Collection<LoteCampaniaEntity> getLoteCampaniasByCnaId() {
        return loteCampaniasByCnaId;
    }

    public void setLoteCampaniasByCnaId(Collection<LoteCampaniaEntity> loteCampaniasByCnaId) {
        this.loteCampaniasByCnaId = loteCampaniasByCnaId;
    }

//=================================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CampaniaEntity that = (CampaniaEntity) o;

        if (cnaId != null ? !cnaId.equals(that.cnaId) : that.cnaId != null) return false;
        if (cnaDenominacion != null ? !cnaDenominacion.equals(that.cnaDenominacion) : that.cnaDenominacion != null)
            return false;
        if (cnaFechaInicio != null ? !cnaFechaInicio.equals(that.cnaFechaInicio) : that.cnaFechaInicio != null)
            return false;
        if (cnaFechaFinEstimada != null ? !cnaFechaFinEstimada.equals(that.cnaFechaFinEstimada) : that.cnaFechaFinEstimada != null)
            return false;
        if (cnaFechaFinReal != null ? !cnaFechaFinReal.equals(that.cnaFechaFinReal) : that.cnaFechaFinReal != null)
            return false;
        if (cnaFechaAlta != null ? !cnaFechaAlta.equals(that.cnaFechaAlta) : that.cnaFechaAlta != null) return false;
        if (cnaUsuarioAlta != null ? !cnaUsuarioAlta.equals(that.cnaUsuarioAlta) : that.cnaUsuarioAlta != null)
            return false;
        if (cnaFechaUltMod != null ? !cnaFechaUltMod.equals(that.cnaFechaUltMod) : that.cnaFechaUltMod != null)
            return false;
        if (cnaUsuarioUltMod != null ? !cnaUsuarioUltMod.equals(that.cnaUsuarioUltMod) : that.cnaUsuarioUltMod != null)
            return false;
        if (cnaFechaBaja != null ? !cnaFechaBaja.equals(that.cnaFechaBaja) : that.cnaFechaBaja != null) return false;
        if (cnaUsuarioBaja != null ? !cnaUsuarioBaja.equals(that.cnaUsuarioBaja) : that.cnaUsuarioBaja != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cnaId != null ? cnaId.hashCode() : 0;
        result = 31 * result + (cnaDenominacion != null ? cnaDenominacion.hashCode() : 0);
        result = 31 * result + (cnaFechaAlta != null ? cnaFechaAlta.hashCode() : 0);
        result = 31 * result + (cnaFechaBaja != null ? cnaFechaBaja.hashCode() : 0);
        result = 31 * result + (cnaFechaFinEstimada != null ? cnaFechaFinEstimada.hashCode() : 0);
        result = 31 * result + (cnaFechaFinReal != null ? cnaFechaFinReal.hashCode() : 0);
        result = 31 * result + (cnaFechaInicio != null ? cnaFechaInicio.hashCode() : 0);
        result = 31 * result + (cnaFechaUltMod != null ? cnaFechaUltMod.hashCode() : 0);
        result = 31 * result + (cnaUsuarioAlta != null ? cnaUsuarioAlta.hashCode() : 0);
        result = 31 * result + (cnaUsuarioBaja != null ? cnaUsuarioBaja.hashCode() : 0);
        result = 31 * result + (cnaUsuarioUltMod != null ? cnaUsuarioUltMod.hashCode() : 0);
        return result;
    }

    //=================================================================
    @Override
    public String toString() {
        return "CampaniaEntity{" +
                "cnaDenominacion='" + cnaDenominacion + '\'' +
                '}';
    }
}
