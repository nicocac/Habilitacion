package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by OWNER on 5/30/2016.
 */
@Entity
@Table(name = "tipo_cliente", schema = "", catalog = "gestar")
public class TipoClienteEntity {

    private Integer tinId;
    private String tinNombre;
    private String tinDescripcion;
    private Date tinFechaAlta;
    private String tinUsuarioAlta;
    private Date tinFechaUltMod;
    private String tinUsuarioUtlMod;
    private Date tinFechaBaja;
    private String tinUsuarioBaja;

//    private Collection<InsumoEntity> insumosByTinId;


    //=================================================================


    @Id
    @GeneratedValue
    @Column(name = "tin_id")
    public Integer getTinId() {
        return tinId;
    }

    public void setTinId(Integer tinId) {
        this.tinId = tinId;
    }

    @Basic
    @Column(name = "tin_nombre")
    public String getTinNombre() {
        return tinNombre;
    }

    public void setTinNombre(String tinNombre) {
        this.tinNombre = tinNombre;
    }

    @Basic
    @Column(name = "tin_descripcion")
    public String getTinDescripcion() {
        return tinDescripcion;
    }

    public void setTinDescripcion(String tinDescripcion) {
        this.tinDescripcion = tinDescripcion;
    }

    @Basic
    @Column(name = "tin_fecha_alta")
    public Date getTinFechaAlta() {
        return tinFechaAlta;
    }

    public void setTinFechaAlta(Date tinFechaAlta) {
        this.tinFechaAlta = tinFechaAlta;
    }

    @Basic
    @Column(name = "tin_usuario_alta")
    public String getTinUsuarioAlta() {
        return tinUsuarioAlta;
    }

    public void setTinUsuarioAlta(String tinUsuarioAlta) {
        this.tinUsuarioAlta = tinUsuarioAlta;
    }

    @Basic
    @Column(name = "tin_fecha_ult_mod")
    public Date getTinFechaUltMod() {
        return tinFechaUltMod;
    }

    public void setTinFechaUltMod(Date tinFechaUltMod) {
        this.tinFechaUltMod = tinFechaUltMod;
    }

    @Basic
    @Column(name = "tin_usuario_utl_mod")
    public String getTinUsuarioUtlMod() {
        return tinUsuarioUtlMod;
    }

    public void setTinUsuarioUtlMod(String tinUsuarioUtlMod) {
        this.tinUsuarioUtlMod = tinUsuarioUtlMod;
    }

    @Basic
    @Column(name = "tin_fecha_baja")
    public Date getTinFechaBaja() {
        return tinFechaBaja;
    }

    public void setTinFechaBaja(Date tinFechaBaja) {
        this.tinFechaBaja = tinFechaBaja;
    }

    @Basic
    @Column(name = "tin_usuario_baja")
    public String getTinUsuarioBaja() {
        return tinUsuarioBaja;
    }

    public void setTinUsuarioBaja(String tinUsuarioBaja) {
        this.tinUsuarioBaja = tinUsuarioBaja;
    }

//
//    @OneToMany(mappedBy = "tipoInsumoByInsTinId")
//    public Collection<InsumoEntity> getInsumosByTinId() {
//        return insumosByTinId;
//    }
//
//    public void setInsumosByTinId(Collection<InsumoEntity> insumosByTinId) {
//        this.insumosByTinId = insumosByTinId;
//    }


    //=================================================================


    @Override
    public String toString() {
        return tinNombre;
    }

    //=================================================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoClienteEntity that = (TipoClienteEntity) o;

        if (tinId != that.tinId) return false;
        if (tinNombre != null ? !tinNombre.equals(that.tinNombre) : that.tinNombre != null) return false;
        if (tinDescripcion != null ? !tinDescripcion.equals(that.tinDescripcion) : that.tinDescripcion != null)
            return false;
        if (tinFechaAlta != null ? !tinFechaAlta.equals(that.tinFechaAlta) : that.tinFechaAlta != null) return false;
        if (tinUsuarioAlta != null ? !tinUsuarioAlta.equals(that.tinUsuarioAlta) : that.tinUsuarioAlta != null)
            return false;
        if (tinFechaUltMod != null ? !tinFechaUltMod.equals(that.tinFechaUltMod) : that.tinFechaUltMod != null)
            return false;
        if (tinUsuarioUtlMod != null ? !tinUsuarioUtlMod.equals(that.tinUsuarioUtlMod) : that.tinUsuarioUtlMod != null)
            return false;
        if (tinFechaBaja != null ? !tinFechaBaja.equals(that.tinFechaBaja) : that.tinFechaBaja != null) return false;
        if (tinUsuarioBaja != null ? !tinUsuarioBaja.equals(that.tinUsuarioBaja) : that.tinUsuarioBaja != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tinId;
        result = 31 * result + (tinDescripcion != null ? tinDescripcion.hashCode() : 0);
        result = 31 * result + (tinFechaAlta != null ? tinFechaAlta.hashCode() : 0);
        result = 31 * result + (tinFechaBaja != null ? tinFechaBaja.hashCode() : 0);
        result = 31 * result + (tinFechaUltMod != null ? tinFechaUltMod.hashCode() : 0);
        result = 31 * result + (tinNombre != null ? tinNombre.hashCode() : 0);
        result = 31 * result + (tinUsuarioAlta != null ? tinUsuarioAlta.hashCode() : 0);
        result = 31 * result + (tinUsuarioBaja != null ? tinUsuarioBaja.hashCode() : 0);
        result = 31 * result + (tinUsuarioUtlMod != null ? tinUsuarioUtlMod.hashCode() : 0);
        return result;
    }



}
