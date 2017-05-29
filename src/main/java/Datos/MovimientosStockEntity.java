package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 5/30/2016.
 */
//@Entity
//@Table(name = "movimientos_stock", schema = "", catalog = "gestar")
public class MovimientosStockEntity {

    private Integer mosId;
    private String mosTipoMovimiento;
    private int mosCantidad;
    private Date mosFechaAlta;
    private String mosUsuarioAlta;
    private Date mosFechaUltMod;
    private String mosUsuarioUtlMod;
    private Date mosFechaBaja;
    private String mosUsuarioBaja;

    private StockInsumoEntity stockInsumoByMosSInsId;

    //======================================================================================


    @Id
    @GeneratedValue
    @Column(name = "mos_id")
    public Integer getMosId() {
        return mosId;
    }

    public void setMosId(Integer mosId) {
        this.mosId = mosId;
    }

    @Basic
    @Column(name = "mos_tipo_movimiento")
    public String getMosTipoMovimiento() {
        return mosTipoMovimiento;
    }

    public void setMosTipoMovimiento(String mosTipoMovimiento) {
        this.mosTipoMovimiento = mosTipoMovimiento;
    }

    @Basic
    @Column(name = "mos_cantidad")
    public int getMosCantidad() {
        return mosCantidad;
    }

    public void setMosCantidad(int mosCantidad) {
        this.mosCantidad = mosCantidad;
    }

    @Basic
    @Column(name = "mos_fecha_alta")
    public Date getMosFechaAlta() {
        return mosFechaAlta;
    }

    public void setMosFechaAlta(Date mosFechaAlta) {
        this.mosFechaAlta = mosFechaAlta;
    }

    @Basic
    @Column(name = "mos_usuario_alta")
    public String getMosUsuarioAlta() {
        return mosUsuarioAlta;
    }

    public void setMosUsuarioAlta(String mosUsuarioAlta) {
        this.mosUsuarioAlta = mosUsuarioAlta;
    }

    @Basic
    @Column(name = "mos_fecha_ult_mod")
    public Date getMosFechaUltMod() {
        return mosFechaUltMod;
    }

    public void setMosFechaUltMod(Date mosFechaUltMod) {
        this.mosFechaUltMod = mosFechaUltMod;
    }

    @Basic
    @Column(name = "mos_usuario_utl_mod")
    public String getMosUsuarioUtlMod() {
        return mosUsuarioUtlMod;
    }

    public void setMosUsuarioUtlMod(String mosUsuarioUtlMod) {
        this.mosUsuarioUtlMod = mosUsuarioUtlMod;
    }

    @Basic
    @Column(name = "mos_fecha_baja")
    public Date getMosFechaBaja() {
        return mosFechaBaja;
    }

    public void setMosFechaBaja(Date mosFechaBaja) {
        this.mosFechaBaja = mosFechaBaja;
    }

    @Basic
    @Column(name = "mos_usuario_baja")
    public String getMosUsuarioBaja() {
        return mosUsuarioBaja;
    }

    public void setMosUsuarioBaja(String mosUsuarioBaja) {
        this.mosUsuarioBaja = mosUsuarioBaja;
    }


    @ManyToOne
    @JoinColumn(name = "mos_s_ins_id", referencedColumnName = "s_ins_id")
    public StockInsumoEntity getStockInsumoByMosSInsId() {
        return stockInsumoByMosSInsId;
    }
    public void setStockInsumoByMosSInsId(StockInsumoEntity stockInsumoByMosSInsId) {
        this.stockInsumoByMosSInsId = stockInsumoByMosSInsId;
    }

    //=================================================================

    //=================================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovimientosStockEntity that = (MovimientosStockEntity) o;

        if (mosId != that.mosId) return false;
        if (mosCantidad != that.mosCantidad) return false;
        if (mosTipoMovimiento != null ? !mosTipoMovimiento.equals(that.mosTipoMovimiento) : that.mosTipoMovimiento != null)
            return false;
        if (mosFechaAlta != null ? !mosFechaAlta.equals(that.mosFechaAlta) : that.mosFechaAlta != null) return false;
        if (mosUsuarioAlta != null ? !mosUsuarioAlta.equals(that.mosUsuarioAlta) : that.mosUsuarioAlta != null)
            return false;
        if (mosFechaUltMod != null ? !mosFechaUltMod.equals(that.mosFechaUltMod) : that.mosFechaUltMod != null)
            return false;
        if (mosUsuarioUtlMod != null ? !mosUsuarioUtlMod.equals(that.mosUsuarioUtlMod) : that.mosUsuarioUtlMod != null)
            return false;
        if (mosFechaBaja != null ? !mosFechaBaja.equals(that.mosFechaBaja) : that.mosFechaBaja != null) return false;
        if (mosUsuarioBaja != null ? !mosUsuarioBaja.equals(that.mosUsuarioBaja) : that.mosUsuarioBaja != null)
            return false;
        if (stockInsumoByMosSInsId != null ? !stockInsumoByMosSInsId.equals(that.stockInsumoByMosSInsId) : that.stockInsumoByMosSInsId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mosId;
        result = 31 * result + mosCantidad;
        result = 31 * result + (mosFechaAlta != null ? mosFechaAlta.hashCode() : 0);
        result = 31 * result + (mosFechaBaja != null ? mosFechaBaja.hashCode() : 0);
        result = 31 * result + (mosFechaUltMod != null ? mosFechaUltMod.hashCode() : 0);
        result = 31 * result + (mosTipoMovimiento != null ? mosTipoMovimiento.hashCode() : 0);
        result = 31 * result + (mosUsuarioAlta != null ? mosUsuarioAlta.hashCode() : 0);
        result = 31 * result + (mosUsuarioBaja != null ? mosUsuarioBaja.hashCode() : 0);
        result = 31 * result + (mosUsuarioUtlMod != null ? mosUsuarioUtlMod.hashCode() : 0);
        result = 31 * result + (stockInsumoByMosSInsId != null ? stockInsumoByMosSInsId.hashCode() : 0);
        return result;
    }
}
