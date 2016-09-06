package Datos;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "tipo_laboreo", schema = "gestar", catalog = "")
public class TipoLaboreoEntity {
    private int tpoId;
    private String tpoNombre;
    private String tpoDescripcion;
    private Date tpoFechaAlta;
    private String tpoUsuarioAlta;
    private Date tpoFechaUltMod;
    private String tpoUsuarioUltMod;
    private Date tpoFechaBaja;
    private String tpoUsuarioBaja;

    @Id
    @GeneratedValue
    @Column(name = "tpo_id")
    public int getTpoId() {
        return tpoId;
    }

    public void setTpoId(int tpoId) {
        this.tpoId = tpoId;
    }

    @Basic
    @Column(name = "tpo_nombre")
    public String getTpoNombre() {
        return tpoNombre;
    }

    public void setTpoNombre(String tpoNombre) {
        this.tpoNombre = tpoNombre;
    }

    @Basic
    @Column(name = "tpo_descripcion")
    public String getTpoDescripcion() {
        return tpoDescripcion;
    }

    public void setTpoDescripcion(String tpoDescripcion) {
        this.tpoDescripcion = tpoDescripcion;
    }

    @Basic
    @Column(name = "tpo_fecha_alta")
    public Date getTpoFechaAlta() {
        return tpoFechaAlta;
    }

    public void setTpoFechaAlta(Date tpoFechaAlta) {
        this.tpoFechaAlta = tpoFechaAlta;
    }

    @Basic
    @Column(name = "tpo_usuario_alta")
    public String getTpoUsuarioAlta() {
        return tpoUsuarioAlta;
    }

    public void setTpoUsuarioAlta(String tpoUsuarioAlta) {
        this.tpoUsuarioAlta = tpoUsuarioAlta;
    }

    @Basic
    @Column(name = "tpo_fecha_ult_mod")
    public Date getTpoFechaUltMod() {
        return tpoFechaUltMod;
    }

    public void setTpoFechaUltMod(Date tpoFechaUltMod) {
        this.tpoFechaUltMod = tpoFechaUltMod;
    }

    @Basic
    @Column(name = "tpo_usuario_ult_mod")
    public String getTpoUsuarioUltMod() {
        return tpoUsuarioUltMod;
    }

    public void setTpoUsuarioUltMod(String tpoUsuarioUltMod) {
        this.tpoUsuarioUltMod = tpoUsuarioUltMod;
    }

    @Basic
    @Column(name = "tpo_fecha_baja")
    public Date getTpoFechaBaja() {
        return tpoFechaBaja;
    }

    public void setTpoFechaBaja(Date tpoFechaBaja) {
        this.tpoFechaBaja = tpoFechaBaja;
    }

    @Basic
    @Column(name = "tpo_usuario_baja")
    public String getTpoUsuarioBaja() {
        return tpoUsuarioBaja;
    }

    public void setTpoUsuarioBaja(String tpoUsuarioBaja) {
        this.tpoUsuarioBaja = tpoUsuarioBaja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoLaboreoEntity that = (TipoLaboreoEntity) o;

        if (tpoId != that.tpoId) return false;
        if (tpoNombre != null ? !tpoNombre.equals(that.tpoNombre) : that.tpoNombre != null) return false;
        if (tpoDescripcion != null ? !tpoDescripcion.equals(that.tpoDescripcion) : that.tpoDescripcion != null)
            return false;
        if (tpoFechaAlta != null ? !tpoFechaAlta.equals(that.tpoFechaAlta) : that.tpoFechaAlta != null) return false;
        if (tpoUsuarioAlta != null ? !tpoUsuarioAlta.equals(that.tpoUsuarioAlta) : that.tpoUsuarioAlta != null)
            return false;
        if (tpoFechaUltMod != null ? !tpoFechaUltMod.equals(that.tpoFechaUltMod) : that.tpoFechaUltMod != null)
            return false;
        if (tpoUsuarioUltMod != null ? !tpoUsuarioUltMod.equals(that.tpoUsuarioUltMod) : that.tpoUsuarioUltMod != null)
            return false;
        if (tpoFechaBaja != null ? !tpoFechaBaja.equals(that.tpoFechaBaja) : that.tpoFechaBaja != null) return false;
        if (tpoUsuarioBaja != null ? !tpoUsuarioBaja.equals(that.tpoUsuarioBaja) : that.tpoUsuarioBaja != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tpoId;
        result = 31 * result + (tpoNombre != null ? tpoNombre.hashCode() : 0);
        result = 31 * result + (tpoDescripcion != null ? tpoDescripcion.hashCode() : 0);
        result = 31 * result + (tpoFechaAlta != null ? tpoFechaAlta.hashCode() : 0);
        result = 31 * result + (tpoUsuarioAlta != null ? tpoUsuarioAlta.hashCode() : 0);
        result = 31 * result + (tpoFechaUltMod != null ? tpoFechaUltMod.hashCode() : 0);
        result = 31 * result + (tpoUsuarioUltMod != null ? tpoUsuarioUltMod.hashCode() : 0);
        result = 31 * result + (tpoFechaBaja != null ? tpoFechaBaja.hashCode() : 0);
        result = 31 * result + (tpoUsuarioBaja != null ? tpoUsuarioBaja.hashCode() : 0);
        return result;
    }
}
