package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by OWNER on 5/30/2016.
 */
@Entity
@Table(name = "stock_insumo", schema = "gestar", catalog = "")
public class StockInsumoEntity {
    private int sinId;
    private int sinTotal;
    private int sinInsId;
    private Date sinFechaAlta;
    private String sinUsuarioAlta;
    private Date sinFechaUltMod;
    private String sinUsuarioUtlMod;
    private Date sinFechaBaja;
    private String sinUsuarioBaja;
    private Collection<MovimientosStockEntity> movimientosStocksBySinId;
    private InsumoEntity insumoBySinInsId;

    @Id
    @Column(name = "sin_id")
    public int getSinId() {
        return sinId;
    }

    public void setSinId(int sinId) {
        this.sinId = sinId;
    }

    @Basic
    @Column(name = "sin_total")
    public int getSinTotal() {
        return sinTotal;
    }

    public void setSinTotal(int sinTotal) {
        this.sinTotal = sinTotal;
    }

    /*@Basic
    @Column(name = "sin_ins_id")
    public int getSinInsId() {
        return sinInsId;
    }

    public void setSinInsId(int sinInsId) {
        this.sinInsId = sinInsId;
    }*/

    @Basic
    @Column(name = "sin_fecha_alta")
    public Date getSinFechaAlta() {
        return sinFechaAlta;
    }

    public void setSinFechaAlta(Date sinFechaAlta) {
        this.sinFechaAlta = sinFechaAlta;
    }

    @Basic
    @Column(name = "sin_usuario_alta")
    public String getSinUsuarioAlta() {
        return sinUsuarioAlta;
    }

    public void setSinUsuarioAlta(String sinUsuarioAlta) {
        this.sinUsuarioAlta = sinUsuarioAlta;
    }

    @Basic
    @Column(name = "sin_fecha_ult_mod")
    public Date getSinFechaUltMod() {
        return sinFechaUltMod;
    }

    public void setSinFechaUltMod(Date sinFechaUltMod) {
        this.sinFechaUltMod = sinFechaUltMod;
    }

    @Basic
    @Column(name = "sin_usuario_utl_mod")
    public String getSinUsuarioUtlMod() {
        return sinUsuarioUtlMod;
    }

    public void setSinUsuarioUtlMod(String sinUsuarioUtlMod) {
        this.sinUsuarioUtlMod = sinUsuarioUtlMod;
    }

    @Basic
    @Column(name = "sin_fecha_baja")
    public Date getSinFechaBaja() {
        return sinFechaBaja;
    }

    public void setSinFechaBaja(Date sinFechaBaja) {
        this.sinFechaBaja = sinFechaBaja;
    }

    @Basic
    @Column(name = "sin_usuario_baja")
    public String getSinUsuarioBaja() {
        return sinUsuarioBaja;
    }

    public void setSinUsuarioBaja(String sinUsuarioBaja) {
        this.sinUsuarioBaja = sinUsuarioBaja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockInsumoEntity that = (StockInsumoEntity) o;

        if (sinId != that.sinId) return false;
        if (sinTotal != that.sinTotal) return false;
        if (sinInsId != that.sinInsId) return false;
        if (sinFechaAlta != null ? !sinFechaAlta.equals(that.sinFechaAlta) : that.sinFechaAlta != null) return false;
        if (sinUsuarioAlta != null ? !sinUsuarioAlta.equals(that.sinUsuarioAlta) : that.sinUsuarioAlta != null)
            return false;
        if (sinFechaUltMod != null ? !sinFechaUltMod.equals(that.sinFechaUltMod) : that.sinFechaUltMod != null)
            return false;
        if (sinUsuarioUtlMod != null ? !sinUsuarioUtlMod.equals(that.sinUsuarioUtlMod) : that.sinUsuarioUtlMod != null)
            return false;
        if (sinFechaBaja != null ? !sinFechaBaja.equals(that.sinFechaBaja) : that.sinFechaBaja != null) return false;
        if (sinUsuarioBaja != null ? !sinUsuarioBaja.equals(that.sinUsuarioBaja) : that.sinUsuarioBaja != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sinId;
        result = 31 * result + sinTotal;
        result = 31 * result + sinInsId;
        result = 31 * result + (sinFechaAlta != null ? sinFechaAlta.hashCode() : 0);
        result = 31 * result + (sinUsuarioAlta != null ? sinUsuarioAlta.hashCode() : 0);
        result = 31 * result + (sinFechaUltMod != null ? sinFechaUltMod.hashCode() : 0);
        result = 31 * result + (sinUsuarioUtlMod != null ? sinUsuarioUtlMod.hashCode() : 0);
        result = 31 * result + (sinFechaBaja != null ? sinFechaBaja.hashCode() : 0);
        result = 31 * result + (sinUsuarioBaja != null ? sinUsuarioBaja.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "stockInsumoByMosSinId")
    public Collection<MovimientosStockEntity> getMovimientosStocksBySinId() {
        return movimientosStocksBySinId;
    }

    public void setMovimientosStocksBySinId(Collection<MovimientosStockEntity> movimientosStocksBySinId) {
        this.movimientosStocksBySinId = movimientosStocksBySinId;
    }

    @ManyToOne
    @JoinColumn(name = "sin_ins_id", referencedColumnName = "ins_id", nullable = false)
    public InsumoEntity getInsumoBySinInsId() {
        return insumoBySinInsId;
    }

    public void setInsumoBySinInsId(InsumoEntity insumoBySinInsId) {
        this.insumoBySinInsId = insumoBySinInsId;
    }
}
