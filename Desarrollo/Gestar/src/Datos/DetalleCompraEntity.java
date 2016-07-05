package Datos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by OWNER on 7/3/2016.
 */
@Entity
@Table(name = "detalle_compra", schema = "gestar")
public class DetalleCompraEntity {
    private int cpdId;
    private BigDecimal cpdCantidad;
    private BigDecimal cpdPrecio;
    private String cpdObservaciones;
    private Date cpdFechaAlta;
    private String cpdUsuarioAlta;
    private Date cpdFechaUltMod;
    private String cpdUsuarioUltMod;
    private Date cpdFechaBaja;
    private String cpdUsuarioBaja;
    private CompraEntity compraByCpdCpaId;
    private InsumoEntity insumoByCpdInsId;

    @Id
    @GeneratedValue
    @Column(name = "cpd_id")
    public int getCpdId() {
        return cpdId;
    }

    public void setCpdId(int cpdId) {
        this.cpdId = cpdId;
    }

    @Basic
    @Column(name = "cpd_cantidad")
    public BigDecimal getCpdCantidad() {
        return cpdCantidad;
    }

    public void setCpdCantidad(BigDecimal cpdCantidad) {
        this.cpdCantidad = cpdCantidad;
    }

    @Basic
    @Column(name = "cpd_precio")
    public BigDecimal getCpdPrecio() {
        return cpdPrecio;
    }

    public void setCpdPrecio(BigDecimal cpdPrecio) {
        this.cpdPrecio = cpdPrecio;
    }

    @Basic
    @Column(name = "cpd_observaciones")
    public String getCpdObservaciones() {
        return cpdObservaciones;
    }

    public void setCpdObservaciones(String cpdObservaciones) {
        this.cpdObservaciones = cpdObservaciones;
    }

    @Basic
    @Column(name = "cpd_fecha_alta")
    public Date getCpdFechaAlta() {
        return cpdFechaAlta;
    }

    public void setCpdFechaAlta(Date cpdFechaAlta) {
        this.cpdFechaAlta = cpdFechaAlta;
    }

    @Basic
    @Column(name = "cpd_usuario_alta")
    public String getCpdUsuarioAlta() {
        return cpdUsuarioAlta;
    }

    public void setCpdUsuarioAlta(String cpdUsuarioAlta) {
        this.cpdUsuarioAlta = cpdUsuarioAlta;
    }

    @Basic
    @Column(name = "cpd_fecha_ult_mod")
    public Date getCpdFechaUltMod() {
        return cpdFechaUltMod;
    }

    public void setCpdFechaUltMod(Date cpdFechaUltMod) {
        this.cpdFechaUltMod = cpdFechaUltMod;
    }

    @Basic
    @Column(name = "cpd_usuario_ult_mod")
    public String getCpdUsuarioUltMod() {
        return cpdUsuarioUltMod;
    }

    public void setCpdUsuarioUltMod(String cpdUsuarioUltMod) {
        this.cpdUsuarioUltMod = cpdUsuarioUltMod;
    }

    @Basic
    @Column(name = "cpd_fecha_baja")
    public Date getCpdFechaBaja() {
        return cpdFechaBaja;
    }

    public void setCpdFechaBaja(Date cpdFechaBaja) {
        this.cpdFechaBaja = cpdFechaBaja;
    }

    @Basic
    @Column(name = "cpd_usuario_baja")
    public String getCpdUsuarioBaja() {
        return cpdUsuarioBaja;
    }

    public void setCpdUsuarioBaja(String cpdUsuarioBaja) {
        this.cpdUsuarioBaja = cpdUsuarioBaja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetalleCompraEntity that = (DetalleCompraEntity) o;

        if (cpdId != that.cpdId) return false;
        if (cpdCantidad != null ? !cpdCantidad.equals(that.cpdCantidad) : that.cpdCantidad != null) return false;
        if (cpdPrecio != null ? !cpdPrecio.equals(that.cpdPrecio) : that.cpdPrecio != null) return false;
        if (cpdObservaciones != null ? !cpdObservaciones.equals(that.cpdObservaciones) : that.cpdObservaciones != null)
            return false;
        if (cpdFechaAlta != null ? !cpdFechaAlta.equals(that.cpdFechaAlta) : that.cpdFechaAlta != null) return false;
        if (cpdUsuarioAlta != null ? !cpdUsuarioAlta.equals(that.cpdUsuarioAlta) : that.cpdUsuarioAlta != null)
            return false;
        if (cpdFechaUltMod != null ? !cpdFechaUltMod.equals(that.cpdFechaUltMod) : that.cpdFechaUltMod != null)
            return false;
        if (cpdUsuarioUltMod != null ? !cpdUsuarioUltMod.equals(that.cpdUsuarioUltMod) : that.cpdUsuarioUltMod != null)
            return false;
        if (cpdFechaBaja != null ? !cpdFechaBaja.equals(that.cpdFechaBaja) : that.cpdFechaBaja != null) return false;
        if (cpdUsuarioBaja != null ? !cpdUsuarioBaja.equals(that.cpdUsuarioBaja) : that.cpdUsuarioBaja != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cpdId;
        result = 31 * result + (cpdCantidad != null ? cpdCantidad.hashCode() : 0);
        result = 31 * result + (cpdPrecio != null ? cpdPrecio.hashCode() : 0);
        result = 31 * result + (cpdObservaciones != null ? cpdObservaciones.hashCode() : 0);
        result = 31 * result + (cpdFechaAlta != null ? cpdFechaAlta.hashCode() : 0);
        result = 31 * result + (cpdUsuarioAlta != null ? cpdUsuarioAlta.hashCode() : 0);
        result = 31 * result + (cpdFechaUltMod != null ? cpdFechaUltMod.hashCode() : 0);
        result = 31 * result + (cpdUsuarioUltMod != null ? cpdUsuarioUltMod.hashCode() : 0);
        result = 31 * result + (cpdFechaBaja != null ? cpdFechaBaja.hashCode() : 0);
        result = 31 * result + (cpdUsuarioBaja != null ? cpdUsuarioBaja.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "cpd_cpa_id", referencedColumnName = "cpa_id")
    public CompraEntity getCompraByCpdCpaId() {
        return compraByCpdCpaId;
    }

    public void setCompraByCpdCpaId(CompraEntity compraByCpdCpaId) {
        this.compraByCpdCpaId = compraByCpdCpaId;
    }

    @ManyToOne
    @JoinColumn(name = "cpd_ins_id", referencedColumnName = "ins_id")
    public InsumoEntity getInsumoByCpdInsId() {
        return insumoByCpdInsId;
    }

    public void setInsumoByCpdInsId(InsumoEntity insumoByCpdInsId) {
        this.insumoByCpdInsId = insumoByCpdInsId;
    }

}
