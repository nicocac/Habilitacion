package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "detalle_laboreo", schema = "", catalog = "gestar")
public class DetalleLaboreoEntity {


    private Integer dboId;
    private String dboObservaciones;
    private int dboCantidad;

    private Date dboFechaAlta;
    private String dboUsuarioAlta;
    private Date dboFechaUltMod;
    private String dboUsuarioUltMod;
    private Date dboFechaBaja;
    private String dboUsuarioBaja;


    private InsumoEntity insumoByDboInsId;
    private LaboreoEntity laboreoByDboLboId;
    private MaquinariaEntity maquinariaByDboMaqId;

    private TipoGranoEntity tipoGrano;
    //=================================================================
    private Integer dboInsId;
    private Integer dboLboId;
    private Integer dboMaqId;
    //======================================================================================


    @Id
    @GeneratedValue
    @Column(name = "dbo_id")
    public Integer getDboId() {
        return dboId;
    }

    public void setDboId(Integer dboId) {
        this.dboId = dboId;
    }

    @Basic
    @Column(name = "dbo_observaciones")
    public String getDboObservaciones() {
        return dboObservaciones;
    }

    public void setDboObservaciones(String dboObservaciones) {
        this.dboObservaciones = dboObservaciones;
    }

    @Basic
    @Column(name = "dbo_cantidad")
    public int getDboCantidad() {
        return dboCantidad;
    }

    public void setDboCantidad(int dboCantidad) {
        this.dboCantidad = dboCantidad;
    }

    @Basic
    @Column(name = "dbo_fecha_alta")
    public Date getDboFechaAlta() {
        return dboFechaAlta;
    }

    public void setDboFechaAlta(Date dboFechaAlta) {
        this.dboFechaAlta = dboFechaAlta;
    }

    @Basic
    @Column(name = "dbo_usuario_alta")
    public String getDboUsuarioAlta() {
        return dboUsuarioAlta;
    }

    public void setDboUsuarioAlta(String dboUsuarioAlta) {
        this.dboUsuarioAlta = dboUsuarioAlta;
    }

    @Basic
    @Column(name = "dbo_fecha_ult_mod")
    public Date getDboFechaUltMod() {
        return dboFechaUltMod;
    }

    public void setDboFechaUltMod(Date dboFechaUltMod) {
        this.dboFechaUltMod = dboFechaUltMod;
    }

    @Basic
    @Column(name = "dbo_usuario_ult_mod")
    public String getDboUsuarioUltMod() {
        return dboUsuarioUltMod;
    }

    public void setDboUsuarioUltMod(String dboUsuarioUltMod) {
        this.dboUsuarioUltMod = dboUsuarioUltMod;
    }

    @Basic
    @Column(name = "dbo_fecha_baja")
    public Date getDboFechaBaja() {
        return dboFechaBaja;
    }

    public void setDboFechaBaja(Date dboFechaBaja) {
        this.dboFechaBaja = dboFechaBaja;
    }

    @Basic
    @Column(name = "dbo_usuario_baja")
    public String getDboUsuarioBaja() {
        return dboUsuarioBaja;
    }

    public void setDboUsuarioBaja(String dboUsuarioBaja) {
        this.dboUsuarioBaja = dboUsuarioBaja;
    }

    @ManyToOne
    @JoinColumn(name = "dbo_ins_id", referencedColumnName = "ins_id", nullable = false)
    public InsumoEntity getInsumoByDboInsId() {
        return insumoByDboInsId;
    }

    public void setInsumoByDboInsId(InsumoEntity insumoByDboInsId) {
        this.insumoByDboInsId = insumoByDboInsId;
    }

    @ManyToOne
    @JoinColumn(name = "dbo_lbo_id", referencedColumnName = "lbo_id", nullable = false)
    public LaboreoEntity getLaboreoByDboLboId() {
        return laboreoByDboLboId;
    }

    public void setLaboreoByDboLboId(LaboreoEntity laboreoByDboLboId) {
        this.laboreoByDboLboId = laboreoByDboLboId;
    }

    @ManyToOne
    @JoinColumn(name = "dbo_maq_id", referencedColumnName = "maq_id")
    public MaquinariaEntity getMaquinariaByDboMaqId() {
        return maquinariaByDboMaqId;
    }

    public void setMaquinariaByDboMaqId(MaquinariaEntity maquinariaByDboMaqId) {
        this.maquinariaByDboMaqId = maquinariaByDboMaqId;
    }

    @ManyToOne
    @JoinColumn(name = "dbo_tipo_grano_id", referencedColumnName = "tgr_id")
    public TipoGranoEntity getTipoGrano() {
        return tipoGrano;
    }

    public void setTipoGrano(TipoGranoEntity tipoGrano) {
        this.tipoGrano = tipoGrano;
    }

    //=================================================================

    @Override
    public String toString() {
        return this.dboId.toString();
    }

    //=================================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetalleLaboreoEntity that = (DetalleLaboreoEntity) o;

        if (dboId != that.dboId) return false;
        if (dboCantidad != that.dboCantidad) return false;
        if (dboObservaciones != null ? !dboObservaciones.equals(that.dboObservaciones) : that.dboObservaciones != null)
            return false;
        if (dboFechaAlta != null ? !dboFechaAlta.equals(that.dboFechaAlta) : that.dboFechaAlta != null) return false;
        if (dboUsuarioAlta != null ? !dboUsuarioAlta.equals(that.dboUsuarioAlta) : that.dboUsuarioAlta != null)
            return false;
        if (dboFechaUltMod != null ? !dboFechaUltMod.equals(that.dboFechaUltMod) : that.dboFechaUltMod != null)
            return false;
        if (dboUsuarioUltMod != null ? !dboUsuarioUltMod.equals(that.dboUsuarioUltMod) : that.dboUsuarioUltMod != null)
            return false;
        if (dboFechaBaja != null ? !dboFechaBaja.equals(that.dboFechaBaja) : that.dboFechaBaja != null) return false;
        if (dboUsuarioBaja != null ? !dboUsuarioBaja.equals(that.dboUsuarioBaja) : that.dboUsuarioBaja != null)
            return false;
        if (dboInsId != null ? !dboInsId.equals(that.dboInsId) : that.dboInsId != null) return false;
        if (dboLboId != null ? !dboLboId.equals(that.dboLboId) : that.dboLboId != null) return false;
        if (dboMaqId != null ? !dboMaqId.equals(that.dboMaqId) : that.dboMaqId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dboId;
        result = 31 * result + dboCantidad;
        result = 31 * result + (dboFechaAlta != null ? dboFechaAlta.hashCode() : 0);
        result = 31 * result + (dboFechaBaja != null ? dboFechaBaja.hashCode() : 0);
        result = 31 * result + (dboFechaUltMod != null ? dboFechaUltMod.hashCode() : 0);
        result = 31 * result + (dboObservaciones != null ? dboObservaciones.hashCode() : 0);
        result = 31 * result + (dboUsuarioAlta != null ? dboUsuarioAlta.hashCode() : 0);
        result = 31 * result + (dboUsuarioBaja != null ? dboUsuarioBaja.hashCode() : 0);
        result = 31 * result + (dboUsuarioUltMod != null ? dboUsuarioUltMod.hashCode() : 0);
        result = 31 * result + (dboInsId != null ? dboInsId.hashCode() : 0);
        result = 31 * result + (dboLboId != null ? dboLboId.hashCode() : 0);
        result = 31 * result + (dboMaqId != null ? dboMaqId.hashCode() : 0);
        return result;
    }


    //////////////////////////////
    //=================================================================

    public Integer getDboInsId() {
        return dboInsId;
    }

    public void setDboInsId(Integer dboInsId) {
        this.dboInsId = dboInsId;
    }

    public Integer getDboLboId() {
        return dboLboId;
    }

    public void setDboLboId(Integer dboLboId) {
        this.dboLboId = dboLboId;
    }

    public Integer getDboMaqId() {
        return dboMaqId;
    }

    public void setDboMaqId(Integer dboMaqId) {
        this.dboMaqId = dboMaqId;
    }
}
