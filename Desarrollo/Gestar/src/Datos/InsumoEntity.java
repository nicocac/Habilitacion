package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by OWNER on 5/30/2016.
 */
@Entity
@Table(name = "insumo", schema = "gestar", catalog = "")
public class InsumoEntity {
    private int insId;
    private String insNombre;
    private String insDescripcion;
    private String insUnidadMedida;
    private Date insFechaAlta;
    private String insUsuarioAlta;
    private Date insFechaUltMod;
    private String insUsuarioUtlMod;
    private Date insFechaBaja;
    private String insUsuarioBaja;
    private TipoInsumoEntity tipoInsumoByInsTinId;
    private Collection<StockInsumoEntity> stockInsumosByInsId;

    @Id
    @Column(name = "ins_id")
    public int getInsId() {
        return insId;
    }

    public void setInsId(int insId) {
        this.insId = insId;
    }

    @Basic
    @Column(name = "ins_nombre")
    public String getInsNombre() {
        return insNombre;
    }

    public void setInsNombre(String insNombre) {
        this.insNombre = insNombre;
    }

    @Basic
    @Column(name = "ins_descripcion")
    public String getInsDescripcion() {
        return insDescripcion;
    }

    public void setInsDescripcion(String insDescripcion) {
        this.insDescripcion = insDescripcion;
    }

    @Basic
    @Column(name = "ins_unidad_medida")
    public String getInsUnidadMedida() {
        return insUnidadMedida;
    }

    public void setInsUnidadMedida(String insUnidadMedida) {
        this.insUnidadMedida = insUnidadMedida;
    }

    @Basic
    @Column(name = "ins_fecha_alta")
    public Date getInsFechaAlta() {
        return insFechaAlta;
    }

    public void setInsFechaAlta(Date insFechaAlta) {
        this.insFechaAlta = insFechaAlta;
    }

    @Basic
    @Column(name = "ins_usuario_alta")
    public String getInsUsuarioAlta() {
        return insUsuarioAlta;
    }

    public void setInsUsuarioAlta(String insUsuarioAlta) {
        this.insUsuarioAlta = insUsuarioAlta;
    }

    @Basic
    @Column(name = "ins_fecha_ult_mod")
    public Date getInsFechaUltMod() {
        return insFechaUltMod;
    }

    public void setInsFechaUltMod(Date insFechaUltMod) {
        this.insFechaUltMod = insFechaUltMod;
    }

    @Basic
    @Column(name = "ins_usuario_utl_mod")
    public String getInsUsuarioUtlMod() {
        return insUsuarioUtlMod;
    }

    public void setInsUsuarioUtlMod(String insUsuarioUtlMod) {
        this.insUsuarioUtlMod = insUsuarioUtlMod;
    }

    @Basic
    @Column(name = "ins_fecha_baja")
    public Date getInsFechaBaja() {
        return insFechaBaja;
    }

    public void setInsFechaBaja(Date insFechaBaja) {
        this.insFechaBaja = insFechaBaja;
    }

    @Basic
    @Column(name = "ins_usuario_baja")
    public String getInsUsuarioBaja() {
        return insUsuarioBaja;
    }

    public void setInsUsuarioBaja(String insUsuarioBaja) {
        this.insUsuarioBaja = insUsuarioBaja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InsumoEntity that = (InsumoEntity) o;

        if (insId != that.insId) return false;
        if (insNombre != null ? !insNombre.equals(that.insNombre) : that.insNombre != null) return false;
        if (insDescripcion != null ? !insDescripcion.equals(that.insDescripcion) : that.insDescripcion != null)
            return false;
        if (insUnidadMedida != null ? !insUnidadMedida.equals(that.insUnidadMedida) : that.insUnidadMedida != null)
            return false;
        if (insFechaAlta != null ? !insFechaAlta.equals(that.insFechaAlta) : that.insFechaAlta != null) return false;
        if (insUsuarioAlta != null ? !insUsuarioAlta.equals(that.insUsuarioAlta) : that.insUsuarioAlta != null)
            return false;
        if (insFechaUltMod != null ? !insFechaUltMod.equals(that.insFechaUltMod) : that.insFechaUltMod != null)
            return false;
        if (insUsuarioUtlMod != null ? !insUsuarioUtlMod.equals(that.insUsuarioUtlMod) : that.insUsuarioUtlMod != null)
            return false;
        if (insFechaBaja != null ? !insFechaBaja.equals(that.insFechaBaja) : that.insFechaBaja != null) return false;
        if (insUsuarioBaja != null ? !insUsuarioBaja.equals(that.insUsuarioBaja) : that.insUsuarioBaja != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = insId;
        result = 31 * result + (insNombre != null ? insNombre.hashCode() : 0);
        result = 31 * result + (insDescripcion != null ? insDescripcion.hashCode() : 0);
        result = 31 * result + (insUnidadMedida != null ? insUnidadMedida.hashCode() : 0);
        result = 31 * result + (insFechaAlta != null ? insFechaAlta.hashCode() : 0);
        result = 31 * result + (insUsuarioAlta != null ? insUsuarioAlta.hashCode() : 0);
        result = 31 * result + (insFechaUltMod != null ? insFechaUltMod.hashCode() : 0);
        result = 31 * result + (insUsuarioUtlMod != null ? insUsuarioUtlMod.hashCode() : 0);
        result = 31 * result + (insFechaBaja != null ? insFechaBaja.hashCode() : 0);
        result = 31 * result + (insUsuarioBaja != null ? insUsuarioBaja.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ins_tin_id", referencedColumnName = "tin_id")
    public TipoInsumoEntity getTipoInsumoByInsTinId() {
        return tipoInsumoByInsTinId;
    }

    public void setTipoInsumoByInsTinId(TipoInsumoEntity tipoInsumoByInsTinId) {
        this.tipoInsumoByInsTinId = tipoInsumoByInsTinId;
    }

    @OneToMany(mappedBy = "insumoBySinInsId")
    public Collection<StockInsumoEntity> getStockInsumosByInsId() {
        return stockInsumosByInsId;
    }

    public void setStockInsumosByInsId(Collection<StockInsumoEntity> stockInsumosByInsId) {
        this.stockInsumosByInsId = stockInsumosByInsId;
    }

    @Override
    public String toString(){
        return this.insNombre;
    }
}
