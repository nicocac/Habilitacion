package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by OWNER on 5/30/2016.
 */
@Entity
@Table(name = "tipo_grano", schema = "", catalog = "gestar")
public class TipoGranoEntity {
    private Integer tgrId;
    private String tgrNombre;
    private String tgrDescripcion;
    private Date tgrFechaAlta;
    private String tgrUsuarioAlta;
    private Date tgrFechaUltMod;
    private String tgrUsuarioUtlMod;
    private Date tgrFechaBaja;
    private String tgrUsuarioBaja;

    //=============================================================================================


    @Id
    @GeneratedValue
    @Column(name = "tgr_id")
    public Integer getTgrId() {
        return tgrId;
    }

    public void setTgrId(Integer tgrId) {
        this.tgrId = tgrId;
    }

    @Basic
    @Column(name = "tgr_nombre")
    public String getTgrNombre() {
        return tgrNombre;
    }

    public void setTgrNombre(String tgrNombre) {
        this.tgrNombre = tgrNombre;
    }

    @Basic
    @Column(name = "tgr_descripcion")
    public String getTgrDescripcion() {
        return tgrDescripcion;
    }

    public void setTgrDescripcion(String tgrDescripcion) {
        this.tgrDescripcion = tgrDescripcion;
    }

    @Basic
    @Column(name = "tgr_fecha_alta")
    public Date getTgrFechaAlta() {
        return tgrFechaAlta;
    }

    public void setTgrFechaAlta(Date tgrFechaAlta) {
        this.tgrFechaAlta = tgrFechaAlta;
    }

    @Basic
    @Column(name = "tgr_usuario_alta")
    public String getTgrUsuarioAlta() {
        return tgrUsuarioAlta;
    }

    public void setTgrUsuarioAlta(String tgrUsuarioAlta) {
        this.tgrUsuarioAlta = tgrUsuarioAlta;
    }

    @Basic
    @Column(name = "tgr_fecha_ult_mod")
    public Date getTgrFechaUltMod() {
        return tgrFechaUltMod;
    }

    public void setTgrFechaUltMod(Date tgrFechaUltMod) {
        this.tgrFechaUltMod = tgrFechaUltMod;
    }

    @Basic
    @Column(name = "tgr_usuario_utl_mod")
    public String getTgrUsuarioUtlMod() {
        return tgrUsuarioUtlMod;
    }

    public void setTgrUsuarioUtlMod(String tgrUsuarioUtlMod) {
        this.tgrUsuarioUtlMod = tgrUsuarioUtlMod;
    }

    @Basic
    @Column(name = "tgr_fecha_baja")
    public Date getTgrFechaBaja() {
        return tgrFechaBaja;
    }

    public void setTgrFechaBaja(Date tgrFechaBaja) {
        this.tgrFechaBaja = tgrFechaBaja;
    }

    @Basic
    @Column(name = "tgr_usuario_baja")
    public String getTgrUsuarioBaja() {
        return tgrUsuarioBaja;
    }

    public void setTgrUsuarioBaja(String tgrUsuarioBaja) {
        this.tgrUsuarioBaja = tgrUsuarioBaja;
    }

    //=================================================================
    @Override
    public String toString() {
        return getTgrNombre();
    }

    //=============================================================================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoGranoEntity that = (TipoGranoEntity) o;

        if (tgrId != that.tgrId) return false;
        if (tgrNombre != null ? !tgrNombre.equals(that.tgrNombre) : that.tgrNombre != null) return false;
        if (tgrDescripcion != null ? !tgrDescripcion.equals(that.tgrDescripcion) : that.tgrDescripcion != null)
            return false;
        if (tgrFechaAlta != null ? !tgrFechaAlta.equals(that.tgrFechaAlta) : that.tgrFechaAlta != null) return false;
        if (tgrUsuarioAlta != null ? !tgrUsuarioAlta.equals(that.tgrUsuarioAlta) : that.tgrUsuarioAlta != null)
            return false;
        if (tgrFechaUltMod != null ? !tgrFechaUltMod.equals(that.tgrFechaUltMod) : that.tgrFechaUltMod != null)
            return false;
        if (tgrUsuarioUtlMod != null ? !tgrUsuarioUtlMod.equals(that.tgrUsuarioUtlMod) : that.tgrUsuarioUtlMod != null)
            return false;
        if (tgrFechaBaja != null ? !tgrFechaBaja.equals(that.tgrFechaBaja) : that.tgrFechaBaja != null) return false;
        if (tgrUsuarioBaja != null ? !tgrUsuarioBaja.equals(that.tgrUsuarioBaja) : that.tgrUsuarioBaja != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tgrId;
        result = 31 * result + (tgrDescripcion != null ? tgrDescripcion.hashCode() : 0);
        result = 31 * result + (tgrFechaAlta != null ? tgrFechaAlta.hashCode() : 0);
        result = 31 * result + (tgrFechaBaja != null ? tgrFechaBaja.hashCode() : 0);
        result = 31 * result + (tgrFechaUltMod != null ? tgrFechaUltMod.hashCode() : 0);
        result = 31 * result + (tgrNombre != null ? tgrNombre.hashCode() : 0);
        result = 31 * result + (tgrUsuarioAlta != null ? tgrUsuarioAlta.hashCode() : 0);
        result = 31 * result + (tgrUsuarioBaja != null ? tgrUsuarioBaja.hashCode() : 0);
        result = 31 * result + (tgrUsuarioUtlMod != null ? tgrUsuarioUtlMod.hashCode() : 0);
        return result;
    }
}
