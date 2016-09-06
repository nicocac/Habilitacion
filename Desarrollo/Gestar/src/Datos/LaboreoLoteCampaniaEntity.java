package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 9/4/2016.
 */
@Entity
@Table(name = "laboreo_lote_campania", schema = "gestar", catalog = "")
@IdClass(LaboreoLoteCampaniaEntityPK.class)
public class LaboreoLoteCampaniaEntity {
    private int llc_id;
    private int llcLcpId;
    private int llcLboId;
    private Date llcFechaAlta;
    private String llcUsuarioAlta;
    private Date llcFechaUltMod;
    private String llcUsuarioUltMod;
    private Date llcFechaBaja;
    private String llcUsuarioBaja;
    private LaboreoEntity laboreoByLlcLboId;
    private LoteCampaniaEntity loteCampaniaByLlcLcpId;

    @Id
    @GeneratedValue
    @Column(name = "llc_id")
    public int getLlc_id() {
        return llc_id;
    }

    public void setLlc_id(int llc_id) {
        this.llc_id = llc_id;
    }

    @Id
    @Column(name = "llc_lcp_id")
    public int getLlcLcpId() {
        return llcLcpId;
    }

    public void setLlcLcpId(int llcLcpId) {
        this.llcLcpId = llcLcpId;
    }

    @Id
    @Column(name = "llc_lbo_id")
    public int getLlcLboId() {
        return llcLboId;
    }

    public void setLlcLboId(int llcLboId) {
        this.llcLboId = llcLboId;
    }

    @Basic
    @Column(name = "llc_fecha_alta")
    public Date getLlcFechaAlta() {
        return llcFechaAlta;
    }

    public void setLlcFechaAlta(Date llcFechaAlta) {
        this.llcFechaAlta = llcFechaAlta;
    }

    @Basic
    @Column(name = "llc_usuario_alta")
    public String getLlcUsuarioAlta() {
        return llcUsuarioAlta;
    }

    public void setLlcUsuarioAlta(String llcUsuarioAlta) {
        this.llcUsuarioAlta = llcUsuarioAlta;
    }

    @Basic
    @Column(name = "llc_fecha_ult_mod")
    public Date getLlcFechaUltMod() {
        return llcFechaUltMod;
    }

    public void setLlcFechaUltMod(Date llcFechaUltMod) {
        this.llcFechaUltMod = llcFechaUltMod;
    }

    @Basic
    @Column(name = "llc_usuario_ult_mod")
    public String getLlcUsuarioUltMod() {
        return llcUsuarioUltMod;
    }

    public void setLlcUsuarioUltMod(String llcUsuarioUltMod) {
        this.llcUsuarioUltMod = llcUsuarioUltMod;
    }

    @Basic
    @Column(name = "llc_fecha_baja")
    public Date getLlcFechaBaja() {
        return llcFechaBaja;
    }

    public void setLlcFechaBaja(Date llcFechaBaja) {
        this.llcFechaBaja = llcFechaBaja;
    }

    @Basic
    @Column(name = "llc_usuario_baja")
    public String getLlcUsuarioBaja() {
        return llcUsuarioBaja;
    }

    public void setLlcUsuarioBaja(String llcUsuarioBaja) {
        this.llcUsuarioBaja = llcUsuarioBaja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LaboreoLoteCampaniaEntity that = (LaboreoLoteCampaniaEntity) o;

        if (llcLcpId != that.llcLcpId) return false;
        if (llcLboId != that.llcLboId) return false;
        if (llcFechaAlta != null ? !llcFechaAlta.equals(that.llcFechaAlta) : that.llcFechaAlta != null) return false;
        if (llcUsuarioAlta != null ? !llcUsuarioAlta.equals(that.llcUsuarioAlta) : that.llcUsuarioAlta != null)
            return false;
        if (llcFechaUltMod != null ? !llcFechaUltMod.equals(that.llcFechaUltMod) : that.llcFechaUltMod != null)
            return false;
        if (llcUsuarioUltMod != null ? !llcUsuarioUltMod.equals(that.llcUsuarioUltMod) : that.llcUsuarioUltMod != null)
            return false;
        if (llcFechaBaja != null ? !llcFechaBaja.equals(that.llcFechaBaja) : that.llcFechaBaja != null) return false;
        if (llcUsuarioBaja != null ? !llcUsuarioBaja.equals(that.llcUsuarioBaja) : that.llcUsuarioBaja != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = llcLcpId;
        result = 31 * result + llcLboId;
        result = 31 * result + (llcFechaAlta != null ? llcFechaAlta.hashCode() : 0);
        result = 31 * result + (llcUsuarioAlta != null ? llcUsuarioAlta.hashCode() : 0);
        result = 31 * result + (llcFechaUltMod != null ? llcFechaUltMod.hashCode() : 0);
        result = 31 * result + (llcUsuarioUltMod != null ? llcUsuarioUltMod.hashCode() : 0);
        result = 31 * result + (llcFechaBaja != null ? llcFechaBaja.hashCode() : 0);
        result = 31 * result + (llcUsuarioBaja != null ? llcUsuarioBaja.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "llc_lbo_id", referencedColumnName = "lbo_id", nullable = false, insertable=false, updatable=false)
    public LaboreoEntity getLaboreoByLlcLboId() {
        return laboreoByLlcLboId;
    }

    public void setLaboreoByLlcLboId(LaboreoEntity laboreoByLlcLboId) {
        this.laboreoByLlcLboId = laboreoByLlcLboId;
    }

    @ManyToOne
    @JoinColumn(name = "llc_lcp_id", referencedColumnName = "lcp_id", nullable = false, insertable=false, updatable=false)
    public LoteCampaniaEntity getLoteCampaniaByLlcLcpId() {
        return loteCampaniaByLlcLcpId;
    }

    public void setLoteCampaniaByLlcLcpId(LoteCampaniaEntity loteCampaniaByLlcLcpId) {
        this.loteCampaniaByLlcLcpId = loteCampaniaByLlcLcpId;
    }
}
