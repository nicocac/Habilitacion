package Datos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "detalle_laboreo", schema = "gestar", catalog = "")
public class DetalleLaboreoEntity {
    private int dboId;
    private String dboObservaciones;
    private int dboCantidad;
    private Date dboFechaAlta;
    private String dboUsuarioAlta;
    private Date dboFechaUltMod;
    private String dboUsuarioUltMod;
    private Date dboFechaBaja;
    private String dboUsuarioBaja;

    @Basic
    @Column(name = "dbo_id")
    public int getDboId() {
        return dboId;
    }

    public void setDboId(int dboId) {
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

        return true;
    }

    @Override
    public int hashCode() {
        int result = dboId;
        result = 31 * result + (dboObservaciones != null ? dboObservaciones.hashCode() : 0);
        result = 31 * result + dboCantidad;
        result = 31 * result + (dboFechaAlta != null ? dboFechaAlta.hashCode() : 0);
        result = 31 * result + (dboUsuarioAlta != null ? dboUsuarioAlta.hashCode() : 0);
        result = 31 * result + (dboFechaUltMod != null ? dboFechaUltMod.hashCode() : 0);
        result = 31 * result + (dboUsuarioUltMod != null ? dboUsuarioUltMod.hashCode() : 0);
        result = 31 * result + (dboFechaBaja != null ? dboFechaBaja.hashCode() : 0);
        result = 31 * result + (dboUsuarioBaja != null ? dboUsuarioBaja.hashCode() : 0);
        return result;
    }
}
