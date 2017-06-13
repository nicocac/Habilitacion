package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "detalle_laboreo")
public class DetalleLaboreoEntity {


    private Integer dboId;
    private String dboObservaciones;
    private int dboCantidadInsumo;
    private int dboCantidadMaquinaria;

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
    private TipoLaboreoEntity tipoLaboreo;
    //=================================================================
//    private Integer dboInsId;
//    private Integer dboLboId;
//    private Integer dboMaqId;
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
    @Column(name = "dbo_cantidad_insumo")

    public int getDboCantidadInsumo() {
        return dboCantidadInsumo;
    }

    public void setDboCantidadInsumo(int dboCantidadInsumo) {
        this.dboCantidadInsumo = dboCantidadInsumo;
    }

    @Basic
    @Column(name = "dbo_cantidad_maquinaria")

    public int getDboCantidadMaquinaria() {
        return dboCantidadMaquinaria;
    }

    public void setDboCantidadMaquinaria(int dboCantidadMaquinaria) {
        this.dboCantidadMaquinaria = dboCantidadMaquinaria;
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
    @JoinColumn(name = "dbo_ins_id", referencedColumnName = "ins_id")
    public InsumoEntity getInsumoByDboInsId() {
        return insumoByDboInsId;
    }

    public void setInsumoByDboInsId(InsumoEntity insumoByDboInsId) {
        this.insumoByDboInsId = insumoByDboInsId;
    }

    @ManyToOne
    @JoinColumn(name = "dbo_lbo_id", referencedColumnName = "lbo_id")
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

    @ManyToOne
    @JoinColumn(name = "dbo_tipo_laboreo_id", referencedColumnName = "tpo_id")
    public TipoLaboreoEntity getTipoLaboreo() {
        return tipoLaboreo;
    }

    public void setTipoLaboreo(TipoLaboreoEntity tipoLaboreo) {
        this.tipoLaboreo = tipoLaboreo;
    }

    //=================================================================


}
