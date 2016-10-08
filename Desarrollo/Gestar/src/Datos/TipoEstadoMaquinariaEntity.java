package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;


@Entity
@Table(name = "tipo_estado_maquinaria", schema = "", catalog = "gestar")
public class TipoEstadoMaquinariaEntity {

    private Integer teMaId;
    private String teMaNombre;
    private String teMaDescripcion;
    private Date teMaFechaAlta;
    private String teMaUsuarioAlta;
    private Date teMaFechaUltMod;
    private String teMaUsuarioUtlMod;
    private Date teMaFechaBaja;
    private String teMaUsuarioBaja;

    private Collection<InsumoEntity> insumosByTinId;
    private Collection<MaquinariaEntity> maquinariasByTeMaId;

    //=================================================================

    @Id
    @GeneratedValue
    @Column(name = "te_ma_id")
    public Integer getTeMaId() {
        return teMaId;
    }

    public void setTeMaId(Integer teMaId) {
        this.teMaId = teMaId;
    }

    @Basic
    @Column(name = "te_ma_nombre")
    public String getTeMaNombre() {
        return teMaNombre;
    }

    public void setTeMaNombre(String teMaNombre) {
        this.teMaNombre = teMaNombre;
    }

    @Basic
    @Column(name = "te_ma_descripcion")
    public String getTeMaDescripcion() {
        return teMaDescripcion;
    }

    public void setTeMaDescripcion(String teMaDescripcion) {
        this.teMaDescripcion = teMaDescripcion;
    }

    @Basic
    @Column(name = "te_ma_fecha_alta")
    public Date getTeMaFechaAlta() {
        return teMaFechaAlta;
    }

    public void setTeMaFechaAlta(Date teMaFechaAlta) {
        this.teMaFechaAlta = teMaFechaAlta;
    }

    @Basic
    @Column(name = "te_ma_usuario_alta")
    public String getTeMaUsuarioAlta() {
        return teMaUsuarioAlta;
    }

    public void setTeMaUsuarioAlta(String teMaUsuarioAlta) {
        this.teMaUsuarioAlta = teMaUsuarioAlta;
    }

    @Basic
    @Column(name = "te_ma_fecha_ult_mod")
    public Date getTeMaFechaUltMod() {
        return teMaFechaUltMod;
    }

    public void setTeMaFechaUltMod(Date teMaFechaUltMod) {
        this.teMaFechaUltMod = teMaFechaUltMod;
    }

    @Basic
    @Column(name = "te_ma_usuario_utl_mod")
    public String getTeMaUsuarioUtlMod() {
        return teMaUsuarioUtlMod;
    }

    public void setTeMaUsuarioUtlMod(String teMaUsuarioUtlMod) {
        this.teMaUsuarioUtlMod = teMaUsuarioUtlMod;
    }

    @Basic
    @Column(name = "te_ma_fecha_baja")
    public Date getTeMaFechaBaja() {
        return teMaFechaBaja;
    }

    public void setTeMaFechaBaja(Date teMaFechaBaja) {
        this.teMaFechaBaja = teMaFechaBaja;
    }

    @Basic
    @Column(name = "te_ma_usuario_baja")
    public String getTeMaUsuarioBaja() {
        return teMaUsuarioBaja;
    }

    public void setTeMaUsuarioBaja(String teMaUsuarioBaja) {
        this.teMaUsuarioBaja = teMaUsuarioBaja;
    }
    @OneToMany(mappedBy = "tipoInsumoByInsTinId")
    public Collection<InsumoEntity> getInsumosByTinId() {
        return insumosByTinId;
    }

    public void setInsumosByTinId(Collection<InsumoEntity> insumosByTinId) {
        this.insumosByTinId = insumosByTinId;
    }

    @OneToMany(mappedBy = "tipoEstadoMaquinariaByMaqTestadoId")
    public Collection<MaquinariaEntity> getMaquinariasByTeMaId() {
        return maquinariasByTeMaId;
    }

    public void setMaquinariasByTeMaId(Collection<MaquinariaEntity> maquinariasByTeMaId) {
        this.maquinariasByTeMaId = maquinariasByTeMaId;
    }


    //=================================================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoEstadoMaquinariaEntity that = (TipoEstadoMaquinariaEntity) o;

        if (teMaId != that.teMaId) return false;
        if (teMaNombre != null ? !teMaNombre.equals(that.teMaNombre) : that.teMaNombre != null) return false;
        if (teMaDescripcion != null ? !teMaDescripcion.equals(that.teMaDescripcion) : that.teMaDescripcion != null)
            return false;
        if (teMaFechaAlta != null ? !teMaFechaAlta.equals(that.teMaFechaAlta) : that.teMaFechaAlta != null)
            return false;
        if (teMaUsuarioAlta != null ? !teMaUsuarioAlta.equals(that.teMaUsuarioAlta) : that.teMaUsuarioAlta != null)
            return false;
        if (teMaFechaUltMod != null ? !teMaFechaUltMod.equals(that.teMaFechaUltMod) : that.teMaFechaUltMod != null)
            return false;
        if (teMaUsuarioUtlMod != null ? !teMaUsuarioUtlMod.equals(that.teMaUsuarioUtlMod) : that.teMaUsuarioUtlMod != null)
            return false;
        if (teMaFechaBaja != null ? !teMaFechaBaja.equals(that.teMaFechaBaja) : that.teMaFechaBaja != null)
            return false;
        return !(teMaUsuarioBaja != null ? !teMaUsuarioBaja.equals(that.teMaUsuarioBaja) : that.teMaUsuarioBaja != null);

    }


    @Override
    public int hashCode() {
        int result = teMaId;
        result = 31 * result + (teMaNombre != null ? teMaNombre.hashCode() : 0);
        result = 31 * result + (teMaDescripcion != null ? teMaDescripcion.hashCode() : 0);
        result = 31 * result + (teMaFechaAlta != null ? teMaFechaAlta.hashCode() : 0);
        result = 31 * result + (teMaUsuarioAlta != null ? teMaUsuarioAlta.hashCode() : 0);
        result = 31 * result + (teMaFechaUltMod != null ? teMaFechaUltMod.hashCode() : 0);
        result = 31 * result + (teMaUsuarioUtlMod != null ? teMaUsuarioUtlMod.hashCode() : 0);
        result = 31 * result + (teMaFechaBaja != null ? teMaFechaBaja.hashCode() : 0);
        result = 31 * result + (teMaUsuarioBaja != null ? teMaUsuarioBaja.hashCode() : 0);
        return result;
    }

    //=================================================================


    @Override
    public String toString() {
        return "TipoEstadoMaquinariaEntity{" +
                "teMaNombre='" + teMaNombre + '\'' +
                '}';
    }
}
