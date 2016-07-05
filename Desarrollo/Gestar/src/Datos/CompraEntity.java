package Datos;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Created by OWNER on 7/3/2016.
 */
@Entity
@Table(name = "compra", schema = "gestar")
public class CompraEntity {
    private int cpaId;
    private Date cpaFechaCompra;
    private BigDecimal cpaMontoTotal;
    private Integer cpaCantidadItems;
    private Date cpaFechaAlta;
    private String cpaUsuarioAlta;
    private Date cpaFechaUltMod;
    private String cpaUsuarioUltMod;
    private Date cpaFechaBaja;
    private String cpaUsuarioBaja;
    private List<DetalleCompraEntity> detallesCompra;



    @Id
    @GeneratedValue
    @Column(name = "cpa_id")
    public int getCpaId() {
        return cpaId;
    }

    public void setCpaId(int cpaId) {
        this.cpaId = cpaId;
    }

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name = "cpd_cpa_id", referencedColumnName = "cpa_id")
    @IndexColumn(name="cpd_id")
    public List<DetalleCompraEntity> getDetallesCompra() {
        return detallesCompra;
    }

    public void setDetallesCompra(List<DetalleCompraEntity> detallesCompra) {
        this.detallesCompra = detallesCompra;
    }

    @Basic
    @Column(name = "cpa_fecha_compra")
    public Date getCpaFechaCompra() {
        return cpaFechaCompra;
    }

    public void setCpaFechaCompra(Date cpaFechaCompra) {
        this.cpaFechaCompra = cpaFechaCompra;
    }

    @Basic
    @Column(name = "cpa_monto_total")
    public BigDecimal getCpaMontoTotal() {
        return cpaMontoTotal;
    }

    public void setCpaMontoTotal(BigDecimal cpaMontoTotal) {
        this.cpaMontoTotal = cpaMontoTotal;
    }

    @Basic
    @Column(name = "cpa_cantidad_items")
    public Integer getCpaCantidadItems() {
        return cpaCantidadItems;
    }

    public void setCpaCantidadItems(Integer cpaCantidadItems) {
        this.cpaCantidadItems = cpaCantidadItems;
    }

    @Basic
    @Column(name = "cpa_fecha_alta")
    public Date getCpaFechaAlta() {
        return cpaFechaAlta;
    }

    public void setCpaFechaAlta(Date cpaFechaAlta) {
        this.cpaFechaAlta = cpaFechaAlta;
    }

    @Basic
    @Column(name = "cpa_usuario_alta")
    public String getCpaUsuarioAlta() {
        return cpaUsuarioAlta;
    }

    public void setCpaUsuarioAlta(String cpaUsuarioAlta) {
        this.cpaUsuarioAlta = cpaUsuarioAlta;
    }

    @Basic
    @Column(name = "cpa_fecha_ult_mod")
    public Date getCpaFechaUltMod() {
        return cpaFechaUltMod;
    }

    public void setCpaFechaUltMod(Date cpaFechaUltMod) {
        this.cpaFechaUltMod = cpaFechaUltMod;
    }

    @Basic
    @Column(name = "cpa_usuario_ult_mod")
    public String getCpaUsuarioUltMod() {
        return cpaUsuarioUltMod;
    }

    public void setCpaUsuarioUltMod(String cpaUsuarioUltMod) {
        this.cpaUsuarioUltMod = cpaUsuarioUltMod;
    }

    @Basic
    @Column(name = "cpa_fecha_baja")
    public Date getCpaFechaBaja() {
        return cpaFechaBaja;
    }

    public void setCpaFechaBaja(Date cpaFechaBaja) {
        this.cpaFechaBaja = cpaFechaBaja;
    }

    @Basic
    @Column(name = "cpa_usuario_baja")
    public String getCpaUsuarioBaja() {
        return cpaUsuarioBaja;
    }

    public void setCpaUsuarioBaja(String cpaUsuarioBaja) {
        this.cpaUsuarioBaja = cpaUsuarioBaja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompraEntity that = (CompraEntity) o;

        if (cpaId != that.cpaId) return false;
        if (cpaFechaCompra != null ? !cpaFechaCompra.equals(that.cpaFechaCompra) : that.cpaFechaCompra != null)
            return false;
        if (cpaMontoTotal != null ? !cpaMontoTotal.equals(that.cpaMontoTotal) : that.cpaMontoTotal != null)
            return false;
        if (cpaCantidadItems != null ? !cpaCantidadItems.equals(that.cpaCantidadItems) : that.cpaCantidadItems != null)
            return false;
        if (cpaFechaAlta != null ? !cpaFechaAlta.equals(that.cpaFechaAlta) : that.cpaFechaAlta != null) return false;
        if (cpaUsuarioAlta != null ? !cpaUsuarioAlta.equals(that.cpaUsuarioAlta) : that.cpaUsuarioAlta != null)
            return false;
        if (cpaFechaUltMod != null ? !cpaFechaUltMod.equals(that.cpaFechaUltMod) : that.cpaFechaUltMod != null)
            return false;
        if (cpaUsuarioUltMod != null ? !cpaUsuarioUltMod.equals(that.cpaUsuarioUltMod) : that.cpaUsuarioUltMod != null)
            return false;
        if (cpaFechaBaja != null ? !cpaFechaBaja.equals(that.cpaFechaBaja) : that.cpaFechaBaja != null) return false;
        if (cpaUsuarioBaja != null ? !cpaUsuarioBaja.equals(that.cpaUsuarioBaja) : that.cpaUsuarioBaja != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cpaId;
        result = 31 * result + (cpaFechaCompra != null ? cpaFechaCompra.hashCode() : 0);
        result = 31 * result + (cpaMontoTotal != null ? cpaMontoTotal.hashCode() : 0);
        result = 31 * result + (cpaCantidadItems != null ? cpaCantidadItems.hashCode() : 0);
        result = 31 * result + (cpaFechaAlta != null ? cpaFechaAlta.hashCode() : 0);
        result = 31 * result + (cpaUsuarioAlta != null ? cpaUsuarioAlta.hashCode() : 0);
        result = 31 * result + (cpaFechaUltMod != null ? cpaFechaUltMod.hashCode() : 0);
        result = 31 * result + (cpaUsuarioUltMod != null ? cpaUsuarioUltMod.hashCode() : 0);
        result = 31 * result + (cpaFechaBaja != null ? cpaFechaBaja.hashCode() : 0);
        result = 31 * result + (cpaUsuarioBaja != null ? cpaUsuarioBaja.hashCode() : 0);
        return result;
    }


}
