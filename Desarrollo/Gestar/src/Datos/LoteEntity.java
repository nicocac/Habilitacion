package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "lote", schema = "gestar", catalog = "")
public class LoteEntity {
    private int lteId;
    private String lteDenominacion;
    private int lteCantMetros;
    private String lteUbicacion;
    private Date lteFechaDesde;
    private Date lteFechaHasta;
    private Date lteFechaAlta;
    private String lteUsuarioAlta;
    private Date lteFechaUltMod;
    private String lteUsuarioUltMod;
    private Date lteFechaBaja;
    private String lteUsuarioBaja;

    @Id
    @Column(name = "lte_id")
    public int getLteId() {
        return lteId;
    }

    public void setLteId(int lteId) {
        this.lteId = lteId;
    }

    @Basic
    @Column(name = "lte_denominacion")
    public String getLteDenominacion() {
        return lteDenominacion;
    }

    public void setLteDenominacion(String lteDenominacion) {
        this.lteDenominacion = lteDenominacion;
    }

    @Basic
    @Column(name = "lte_cant_metros")
    public int getLteCantMetros() {
        return lteCantMetros;
    }

    public void setLteCantMetros(int lteCantMetros) {
        this.lteCantMetros = lteCantMetros;
    }

    @Basic
    @Column(name = "lte_ubicacion")
    public String getLteUbicacion() {
        return lteUbicacion;
    }

    public void setLteUbicacion(String lteUbicacion) {
        this.lteUbicacion = lteUbicacion;
    }

    @Basic
    @Column(name = "lte_fecha_desde")
    public Date getLteFechaDesde() {
        return lteFechaDesde;
    }

    public void setLteFechaDesde(Date lteFechaDesde) {
        this.lteFechaDesde = lteFechaDesde;
    }

    @Basic
    @Column(name = "lte_fecha_hasta")
    public Date getLteFechaHasta() {
        return lteFechaHasta;
    }

    public void setLteFechaHasta(Date lteFechaHasta) {
        this.lteFechaHasta = lteFechaHasta;
    }

    @Basic
    @Column(name = "lte_fecha_alta")
    public Date getLteFechaAlta() {
        return lteFechaAlta;
    }

    public void setLteFechaAlta(Date lteFechaAlta) {
        this.lteFechaAlta = lteFechaAlta;
    }

    @Basic
    @Column(name = "lte_usuario_alta")
    public String getLteUsuarioAlta() {
        return lteUsuarioAlta;
    }

    public void setLteUsuarioAlta(String lteUsuarioAlta) {
        this.lteUsuarioAlta = lteUsuarioAlta;
    }

    @Basic
    @Column(name = "lte_fecha_ult_mod")
    public Date getLteFechaUltMod() {
        return lteFechaUltMod;
    }

    public void setLteFechaUltMod(Date lteFechaUltMod) {
        this.lteFechaUltMod = lteFechaUltMod;
    }

    @Basic
    @Column(name = "lte_usuario_ult_mod")
    public String getLteUsuarioUltMod() {
        return lteUsuarioUltMod;
    }

    public void setLteUsuarioUltMod(String lteUsuarioUltMod) {
        this.lteUsuarioUltMod = lteUsuarioUltMod;
    }

    @Basic
    @Column(name = "lte_fecha_baja")
    public Date getLteFechaBaja() {
        return lteFechaBaja;
    }

    public void setLteFechaBaja(Date lteFechaBaja) {
        this.lteFechaBaja = lteFechaBaja;
    }

    @Basic
    @Column(name = "lte_usuario_baja")
    public String getLteUsuarioBaja() {
        return lteUsuarioBaja;
    }

    public void setLteUsuarioBaja(String lteUsuarioBaja) {
        this.lteUsuarioBaja = lteUsuarioBaja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoteEntity that = (LoteEntity) o;

        if (lteId != that.lteId) return false;
        if (lteCantMetros != that.lteCantMetros) return false;
        if (lteDenominacion != null ? !lteDenominacion.equals(that.lteDenominacion) : that.lteDenominacion != null)
            return false;
        if (lteUbicacion != null ? !lteUbicacion.equals(that.lteUbicacion) : that.lteUbicacion != null) return false;
        if (lteFechaDesde != null ? !lteFechaDesde.equals(that.lteFechaDesde) : that.lteFechaDesde != null)
            return false;
        if (lteFechaHasta != null ? !lteFechaHasta.equals(that.lteFechaHasta) : that.lteFechaHasta != null)
            return false;
        if (lteFechaAlta != null ? !lteFechaAlta.equals(that.lteFechaAlta) : that.lteFechaAlta != null) return false;
        if (lteUsuarioAlta != null ? !lteUsuarioAlta.equals(that.lteUsuarioAlta) : that.lteUsuarioAlta != null)
            return false;
        if (lteFechaUltMod != null ? !lteFechaUltMod.equals(that.lteFechaUltMod) : that.lteFechaUltMod != null)
            return false;
        if (lteUsuarioUltMod != null ? !lteUsuarioUltMod.equals(that.lteUsuarioUltMod) : that.lteUsuarioUltMod != null)
            return false;
        if (lteFechaBaja != null ? !lteFechaBaja.equals(that.lteFechaBaja) : that.lteFechaBaja != null) return false;
        if (lteUsuarioBaja != null ? !lteUsuarioBaja.equals(that.lteUsuarioBaja) : that.lteUsuarioBaja != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lteId;
        result = 31 * result + (lteDenominacion != null ? lteDenominacion.hashCode() : 0);
        result = 31 * result + lteCantMetros;
        result = 31 * result + (lteUbicacion != null ? lteUbicacion.hashCode() : 0);
        result = 31 * result + (lteFechaDesde != null ? lteFechaDesde.hashCode() : 0);
        result = 31 * result + (lteFechaHasta != null ? lteFechaHasta.hashCode() : 0);
        result = 31 * result + (lteFechaAlta != null ? lteFechaAlta.hashCode() : 0);
        result = 31 * result + (lteUsuarioAlta != null ? lteUsuarioAlta.hashCode() : 0);
        result = 31 * result + (lteFechaUltMod != null ? lteFechaUltMod.hashCode() : 0);
        result = 31 * result + (lteUsuarioUltMod != null ? lteUsuarioUltMod.hashCode() : 0);
        result = 31 * result + (lteFechaBaja != null ? lteFechaBaja.hashCode() : 0);
        result = 31 * result + (lteUsuarioBaja != null ? lteUsuarioBaja.hashCode() : 0);
        return result;
    }
}
