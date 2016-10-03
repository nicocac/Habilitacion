package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "lote_campania", schema = "gestar", catalog = "")
public class LoteCampaniaEntity {
    private int lcpId;
    private Date lcpFechaInicio;
    private Date lcpFechaFin;
    private Date lcpFechaAlta;
    private String lcpUsuarioAlta;
    private Date lcpFechaUltMod;
    private String lcpUsuarioUltMod;
    private Date lcpFechaBaja;
    private String lcpUsuarioBaja;
    private CampaniaEntity campaniaByLcpCnaId;
    private LoteEntity loteByLcpLteId;
    private Collection<LaboreoLoteCampaniaEntity> laboreoLoteCampaniasByLcpId;

    @Id
    @GeneratedValue
    @Column(name = "lcp_id")
    public int getLcpId() {
        return lcpId;
    }

    public void setLcpId(int lcpId) {
        this.lcpId = lcpId;
    }

    @Basic
    @Column(name = "lcp_fecha_inicio")
    public Date getLcpFechaInicio() {
        return lcpFechaInicio;
    }

    public void setLcpFechaInicio(Date lcpFechaInicio) {
        this.lcpFechaInicio = lcpFechaInicio;
    }

    @Basic
    @Column(name = "lcp_fecha_fin")
    public Date getLcpFechaFin() {
        return lcpFechaFin;
    }

    public void setLcpFechaFin(Date lcpFechaFin) {
        this.lcpFechaFin = lcpFechaFin;
    }

    @Basic
    @Column(name = "lcp_fecha_alta")
    public Date getLcpFechaAlta() {
        return lcpFechaAlta;
    }

    public void setLcpFechaAlta(Date lcpFechaAlta) {
        this.lcpFechaAlta = lcpFechaAlta;
    }

    @Basic
    @Column(name = "lcp_usuario_alta")
    public String getLcpUsuarioAlta() {
        return lcpUsuarioAlta;
    }

    public void setLcpUsuarioAlta(String lcpUsuarioAlta) {
        this.lcpUsuarioAlta = lcpUsuarioAlta;
    }

    @Basic
    @Column(name = "lcp_fecha_ult_mod")
    public Date getLcpFechaUltMod() {
        return lcpFechaUltMod;
    }

    public void setLcpFechaUltMod(Date lcpFechaUltMod) {
        this.lcpFechaUltMod = lcpFechaUltMod;
    }

    @Basic
    @Column(name = "lcp_usuario_ult_mod")
    public String getLcpUsuarioUltMod() {
        return lcpUsuarioUltMod;
    }

    public void setLcpUsuarioUltMod(String lcpUsuarioUltMod) {
        this.lcpUsuarioUltMod = lcpUsuarioUltMod;
    }

    @Basic
    @Column(name = "lcp_fecha_baja")
    public Date getLcpFechaBaja() {
        return lcpFechaBaja;
    }

    public void setLcpFechaBaja(Date lcpFechaBaja) {
        this.lcpFechaBaja = lcpFechaBaja;
    }

    @Basic
    @Column(name = "lcp_usuario_baja")
    public String getLcpUsuarioBaja() {
        return lcpUsuarioBaja;
    }

    public void setLcpUsuarioBaja(String lcpUsuarioBaja) {
        this.lcpUsuarioBaja = lcpUsuarioBaja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoteCampaniaEntity that = (LoteCampaniaEntity) o;

        if (lcpId != that.lcpId) return false;
        if (lcpFechaInicio != null ? !lcpFechaInicio.equals(that.lcpFechaInicio) : that.lcpFechaInicio != null)
            return false;
        if (lcpFechaFin != null ? !lcpFechaFin.equals(that.lcpFechaFin) : that.lcpFechaFin != null) return false;
        if (lcpFechaAlta != null ? !lcpFechaAlta.equals(that.lcpFechaAlta) : that.lcpFechaAlta != null) return false;
        if (lcpUsuarioAlta != null ? !lcpUsuarioAlta.equals(that.lcpUsuarioAlta) : that.lcpUsuarioAlta != null)
            return false;
        if (lcpFechaUltMod != null ? !lcpFechaUltMod.equals(that.lcpFechaUltMod) : that.lcpFechaUltMod != null)
            return false;
        if (lcpUsuarioUltMod != null ? !lcpUsuarioUltMod.equals(that.lcpUsuarioUltMod) : that.lcpUsuarioUltMod != null)
            return false;
        if (lcpFechaBaja != null ? !lcpFechaBaja.equals(that.lcpFechaBaja) : that.lcpFechaBaja != null) return false;
        if (lcpUsuarioBaja != null ? !lcpUsuarioBaja.equals(that.lcpUsuarioBaja) : that.lcpUsuarioBaja != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lcpId;
        result = 31 * result + (lcpFechaInicio != null ? lcpFechaInicio.hashCode() : 0);
        result = 31 * result + (lcpFechaFin != null ? lcpFechaFin.hashCode() : 0);
        result = 31 * result + (lcpFechaAlta != null ? lcpFechaAlta.hashCode() : 0);
        result = 31 * result + (lcpUsuarioAlta != null ? lcpUsuarioAlta.hashCode() : 0);
        result = 31 * result + (lcpFechaUltMod != null ? lcpFechaUltMod.hashCode() : 0);
        result = 31 * result + (lcpUsuarioUltMod != null ? lcpUsuarioUltMod.hashCode() : 0);
        result = 31 * result + (lcpFechaBaja != null ? lcpFechaBaja.hashCode() : 0);
        result = 31 * result + (lcpUsuarioBaja != null ? lcpUsuarioBaja.hashCode() : 0);
        return result;
    }


    @OneToMany(mappedBy = "loteCampaniaByLlcLcpId")
    public Collection<LaboreoLoteCampaniaEntity> getLaboreoLoteCampaniasByLcpId() {
        return laboreoLoteCampaniasByLcpId;
    }

    public void setLaboreoLoteCampaniasByLcpId(Collection<LaboreoLoteCampaniaEntity> laboreoLoteCampaniasByLcpId) {
        this.laboreoLoteCampaniasByLcpId = laboreoLoteCampaniasByLcpId;
    }

    @ManyToOne
    @JoinColumn(name = "lcp_cna_id", referencedColumnName = "cna_id", nullable = false)
    public CampaniaEntity getCampaniaByLcpCnaId() {
        return campaniaByLcpCnaId;
    }

    public void setCampaniaByLcpCnaId(CampaniaEntity campaniaByLcpCnaId) {
        this.campaniaByLcpCnaId = campaniaByLcpCnaId;
    }

    @ManyToOne
    @JoinColumn(name = "lcp_lte_id", referencedColumnName = "lte_id", nullable = false)
    public LoteEntity getLoteByLcpLteId() {
        return loteByLcpLteId;
    }

    public void setLoteByLcpLteId(LoteEntity loteByLcpLteId) {
        this.loteByLcpLteId = loteByLcpLteId;
    }
}
