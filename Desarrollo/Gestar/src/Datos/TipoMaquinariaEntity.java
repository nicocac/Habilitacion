package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;


@Entity
@Table(name = "tipo_maquinaria", schema = "gestar", catalog = "")
public class TipoMaquinariaEntity {

    private int tmaId;
    private String tmaNombre;
    private String tmaDescripcion;
    private Date tmaFechaAlta;
    private String tmaUsuarioAlta;
    private Date tmaFechaUltMod;
    private String tmaUsuarioUtlMod;
    private Date tmaFechaBaja;
    private String tmaUsuarioBaja;
    private Collection<MaquinariaEntity> maquinariasByTmaId;


    @Id
    @GeneratedValue
    @Column(name = "tma_id")
    public int getTmaId() {
        return tmaId;
    }

    public void setTmaId(int tmaId) {
        this.tmaId = tmaId;
    }


    @Basic
    @Column(name = "tma_nombre")
    public String getTmaNombre() {
        return tmaNombre;
    }

    public void setTmaNombre(String tmaNombre) {
        this.tmaNombre = tmaNombre;
    }

    @Basic
    @Column(name = "tma_descripcion")
    public String getTmaDescripcion() {
        return tmaDescripcion;
    }

    public void setTmaDescripcion(String tmaDescripcion) {
        this.tmaDescripcion = tmaDescripcion;
    }

    @Basic
    @Column(name = "tma_fecha_alta")
    public Date getTmaFechaAlta() {
        return tmaFechaAlta;
    }

    public void setTmaFechaAlta(Date tmaFechaAlta) {
        this.tmaFechaAlta = tmaFechaAlta;
    }


    @Basic
    @Column(name = "tma_usuario_alta")
    public String getTmaUsuarioAlta() {
        return tmaUsuarioAlta;
    }

    public void setTmaUsuarioAlta(String tmaUsuarioAlta) {
        this.tmaUsuarioAlta = tmaUsuarioAlta;
    }

    @Basic
    @Column(name = "tma_fecha_ult_mod")
    public Date getTmaFechaUltMod() {
        return tmaFechaUltMod;
    }

    public void setTmaFechaUltMod(Date tmaFechaUltMod) {
        this.tmaFechaUltMod = tmaFechaUltMod;
    }


    @Basic
    @Column(name = "tma_usuario_utl_mod")
    public String getTmaUsuarioUtlMod() {
        return tmaUsuarioUtlMod;
    }

    public void setTmaUsuarioUtlMod(String tmaUsuarioUtlMod) {
        this.tmaUsuarioUtlMod = tmaUsuarioUtlMod;
    }


    @Basic
    @Column(name = "tma_fecha_baja")
    public Date getTmaFechaBaja() {
        return tmaFechaBaja;
    }

    public void setTmaFechaBaja(Date tmaFechaBaja) {
        this.tmaFechaBaja = tmaFechaBaja;
    }

    @Basic
    @Column(name = "tma_usuario_baja")
    public String getTmaUsuarioBaja() {
        return tmaUsuarioBaja;
    }

    public void setTmaUsuarioBaja(String tmaUsuarioBaja) {
        this.tmaUsuarioBaja = tmaUsuarioBaja;
    }

    @OneToMany(mappedBy = "tipoMaquinariaByMaqTmaqId")
    public Collection<MaquinariaEntity> getMaquinariasByTmaId() {
        return maquinariasByTmaId;
    }

    public void setMaquinariasByTmaId(Collection<MaquinariaEntity> maquinariasByTmaId) {
        this.maquinariasByTmaId = maquinariasByTmaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoMaquinariaEntity that = (TipoMaquinariaEntity) o;

        if (tmaId != that.tmaId) return false;
        if (tmaNombre != null ? !tmaNombre.equals(that.tmaNombre) : that.tmaNombre != null) return false;
        if (tmaDescripcion != null ? !tmaDescripcion.equals(that.tmaDescripcion) : that.tmaDescripcion != null)
            return false;
        if (tmaFechaAlta != null ? !tmaFechaAlta.equals(that.tmaFechaAlta) : that.tmaFechaAlta != null) return false;
        if (tmaUsuarioAlta != null ? !tmaUsuarioAlta.equals(that.tmaUsuarioAlta) : that.tmaUsuarioAlta != null)
            return false;
        if (tmaFechaUltMod != null ? !tmaFechaUltMod.equals(that.tmaFechaUltMod) : that.tmaFechaUltMod != null)
            return false;
        if (tmaUsuarioUtlMod != null ? !tmaUsuarioUtlMod.equals(that.tmaUsuarioUtlMod) : that.tmaUsuarioUtlMod != null)
            return false;
        if (tmaFechaBaja != null ? !tmaFechaBaja.equals(that.tmaFechaBaja) : that.tmaFechaBaja != null) return false;
        if (tmaUsuarioBaja != null ? !tmaUsuarioBaja.equals(that.tmaUsuarioBaja) : that.tmaUsuarioBaja != null)
            return false;
        return !(maquinariasByTmaId != null ? !maquinariasByTmaId.equals(that.maquinariasByTmaId) : that.maquinariasByTmaId != null);

    }

    @Override
    public int hashCode() {
        int result = tmaId;
        result = 31 * result + (tmaNombre != null ? tmaNombre.hashCode() : 0);
        result = 31 * result + (tmaDescripcion != null ? tmaDescripcion.hashCode() : 0);
        result = 31 * result + (tmaFechaAlta != null ? tmaFechaAlta.hashCode() : 0);
        result = 31 * result + (tmaUsuarioAlta != null ? tmaUsuarioAlta.hashCode() : 0);
        result = 31 * result + (tmaFechaUltMod != null ? tmaFechaUltMod.hashCode() : 0);
        result = 31 * result + (tmaUsuarioUtlMod != null ? tmaUsuarioUtlMod.hashCode() : 0);
        result = 31 * result + (tmaFechaBaja != null ? tmaFechaBaja.hashCode() : 0);
        result = 31 * result + (tmaUsuarioBaja != null ? tmaUsuarioBaja.hashCode() : 0);
        result = 31 * result + (maquinariasByTmaId != null ? maquinariasByTmaId.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return this.tmaNombre;
    }

}
