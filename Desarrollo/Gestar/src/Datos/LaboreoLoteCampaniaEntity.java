package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 9/4/2016.
 */
@Entity
@Table(name = "laboreo_lote_campania", schema = "", catalog = "gestar")
public class LaboreoLoteCampaniaEntity {
    private Integer llcId;
    private Date llcFechaAlta;
    private String llcUsuarioAlta;
    private Date llcFechaUltMod;
    private String llcUsuarioUltMod;
    private Date llcFechaBaja;
    private String llcUsuarioBaja;

    private LaboreoEntity laboreoByLlcLboId;
    private LoteCampaniaEntity loteCampaniaByLlcLcpId;

    //=================================================================

    private int llcLcpId;
    private int llcLboId;


    //======================================================================================

    @Id
    @GeneratedValue
    @Column(name = "llc_id")
    public Integer getLlcId() {
        return llcId;
    }

    public void setLlcId(Integer llcId) {
        this.llcId = llcId;
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

    @ManyToOne
    @JoinColumn(name = "llc_lbo_id", referencedColumnName = "lbo_id", nullable = false)
    public LaboreoEntity getLaboreoByLlcLboId() {
        return laboreoByLlcLboId;
    }

    public void setLaboreoByLlcLboId(LaboreoEntity laboreoByLlcLboId) {
        this.laboreoByLlcLboId = laboreoByLlcLboId;
    }

    @ManyToOne
    @JoinColumn(name = "llc_lcp_id", referencedColumnName = "lcp_id", nullable = false)
    public LoteCampaniaEntity getLoteCampaniaByLlcLcpId() {
        return loteCampaniaByLlcLcpId;
    }

    public void setLoteCampaniaByLlcLcpId(LoteCampaniaEntity loteCampaniaByLlcLcpId) {
        this.loteCampaniaByLlcLcpId = loteCampaniaByLlcLcpId;
    }

    //=================================================================
    @Override
    public String toString() {
        return "LaboreoLoteCampaniaEntity{" +
                "llc_id=" + llcId +
                '}';
    }


    //=================================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LaboreoLoteCampaniaEntity that = (LaboreoLoteCampaniaEntity) o;

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
        if (laboreoByLlcLboId != null ? !laboreoByLlcLboId.equals(that.laboreoByLlcLboId) : that.laboreoByLlcLboId != null)
            return false;
        if (loteCampaniaByLlcLcpId != null ? !loteCampaniaByLlcLcpId.equals(that.loteCampaniaByLlcLcpId) : that.loteCampaniaByLlcLcpId != null)
            return false;
        if (llcId != null ? !llcId.equals(that.llcId) : that.llcId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = llcId != null ? llcId.hashCode() : 0;
        result = 31 * result + (llcFechaAlta != null ? llcFechaAlta.hashCode() : 0);
        result = 31 * result + (llcFechaBaja != null ? llcFechaBaja.hashCode() : 0);
        result = 31 * result + (llcFechaUltMod != null ? llcFechaUltMod.hashCode() : 0);
        result = 31 * result + (llcUsuarioAlta != null ? llcUsuarioAlta.hashCode() : 0);
        result = 31 * result + (llcUsuarioBaja != null ? llcUsuarioBaja.hashCode() : 0);
        result = 31 * result + (llcUsuarioUltMod != null ? llcUsuarioUltMod.hashCode() : 0);
        result = 31 * result + (laboreoByLlcLboId != null ? laboreoByLlcLboId.hashCode() : 0);
        result = 31 * result + (loteCampaniaByLlcLcpId != null ? loteCampaniaByLlcLcpId.hashCode() : 0);
        return result;
    }


    ////////////////////////////////

    //=================================================================


    public int getLlcLcpId() {
        return llcLcpId;
    }

    public void setLlcLcpId(int llcLcpId) {
        this.llcLcpId = llcLcpId;
    }

    public int getLlcLboId() {
        return llcLboId;
    }

    public void setLlcLboId(int llcLboId) {
        this.llcLboId = llcLboId;
    }

}
