package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "campo", schema = "", catalog = "gestar")
public class CampoEntity {


    private Integer cpoId;
    private String cpoNombre;
    private Integer cpoCantMetros;
    private String cpoObservaciones;
    private String cpoPropietario;
    private String cpoUbicacion;
    private String cpoCoordenadas;
    private Date cpoFechaAlta;
    private String cpoUsuarioAlta;
    private Date cpoFechaUltMod;
    private String cpoUsuarioUltMod;
    private Date cpoFechaBaja;
    private String cpoUsuarioBaja;


    //======================================================================================

    @Id
    @GeneratedValue
    @Column(name = "cpo_id")
    public Integer getCpoId() {
        return cpoId;
    }

    public void setCpoId(Integer cpoId) {
        this.cpoId = cpoId;
    }

    @Basic
    @Column(name = "cpo_nombre")
    public String getCpoNombre() {
        return cpoNombre;
    }

    public void setCpoNombre(String cpoNombre) {
        this.cpoNombre = cpoNombre;
    }

    @Basic
    @Column(name = "cpo_cant_metros")
    public Integer getCpoCantMetros() {
        return cpoCantMetros;
    }

    public void setCpoCantMetros(Integer cpoCantMetros) {
        this.cpoCantMetros = cpoCantMetros;
    }

    @Basic
    @Column(name = "cpo_observaciones")
    public String getCpoObservaciones() {
        return cpoObservaciones;
    }

    public void setCpoObservaciones(String cpoObservaciones) {
        this.cpoObservaciones = cpoObservaciones;
    }

    @Basic
    @Column(name = "cpo_propietario")
    public String getCpoPropietario() {
        return cpoPropietario;
    }

    public void setCpoPropietario(String cpoPropietario) {
        this.cpoPropietario = cpoPropietario;
    }

    @Basic
    @Column(name = "cpo_ubicacion")
    public String getCpoUbicacion() {
        return cpoUbicacion;
    }

    public void setCpoUbicacion(String cpoUbicacion) {
        this.cpoUbicacion = cpoUbicacion;
    }

    @Basic
    @Column(name = "cpo_coordenadas")
    public String getCpoCoordenadas() {
        return cpoCoordenadas;
    }

    public void setCpoCoordenadas(String cpoCoordenadas) {
        this.cpoCoordenadas = cpoCoordenadas;
    }

    @Basic
    @Column(name = "cpo_fecha_alta")
    public Date getCpoFechaAlta() {
        return cpoFechaAlta;
    }

    public void setCpoFechaAlta(Date cpoFechaAlta) {
        this.cpoFechaAlta = cpoFechaAlta;
    }

    @Basic
    @Column(name = "cpo_usuario_alta")
    public String getCpoUsuarioAlta() {
        return cpoUsuarioAlta;
    }

    public void setCpoUsuarioAlta(String cpoUsuarioAlta) {
        this.cpoUsuarioAlta = cpoUsuarioAlta;
    }

    @Basic
    @Column(name = "cpo_fecha_ult_mod")
    public Date getCpoFechaUltMod() {
        return cpoFechaUltMod;
    }

    public void setCpoFechaUltMod(Date cpoFechaUltMod) {
        this.cpoFechaUltMod = cpoFechaUltMod;
    }

    @Basic
    @Column(name = "cpo_usuario_ult_mod")
    public String getCpoUsuarioUltMod() {
        return cpoUsuarioUltMod;
    }

    public void setCpoUsuarioUltMod(String cpoUsuarioUltMod) {
        this.cpoUsuarioUltMod = cpoUsuarioUltMod;
    }

    @Basic
    @Column(name = "cpo_fecha_baja")
    public Date getCpoFechaBaja() {
        return cpoFechaBaja;
    }

    public void setCpoFechaBaja(Date cpoFechaBaja) {
        this.cpoFechaBaja = cpoFechaBaja;
    }

    @Basic
    @Column(name = "cpo_usuario_baja")
    public String getCpoUsuarioBaja() {
        return cpoUsuarioBaja;
    }

    public void setCpoUsuarioBaja(String cpoUsuarioBaja) {
        this.cpoUsuarioBaja = cpoUsuarioBaja;
    }

    //=================================================================
    @Override
    public String toString() {
        return getCpoNombre();
    }

    //=================================================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CampoEntity that = (CampoEntity) o;

        if (cpoId != that.cpoId) return false;
        if (cpoCantMetros != that.cpoCantMetros) return false;
        if (cpoNombre != null ? !cpoNombre.equals(that.cpoNombre) : that.cpoNombre != null) return false;
        if (cpoObservaciones != null ? !cpoObservaciones.equals(that.cpoObservaciones) : that.cpoObservaciones != null)
            return false;
        if (cpoPropietario != null ? !cpoPropietario.equals(that.cpoPropietario) : that.cpoPropietario != null)
            return false;
        if (cpoUbicacion != null ? !cpoUbicacion.equals(that.cpoUbicacion) : that.cpoUbicacion != null) return false;
        if (cpoCoordenadas != null ? !cpoCoordenadas.equals(that.cpoCoordenadas) : that.cpoCoordenadas != null)
            return false;
        if (cpoFechaAlta != null ? !cpoFechaAlta.equals(that.cpoFechaAlta) : that.cpoFechaAlta != null) return false;
        if (cpoUsuarioAlta != null ? !cpoUsuarioAlta.equals(that.cpoUsuarioAlta) : that.cpoUsuarioAlta != null)
            return false;
        if (cpoFechaUltMod != null ? !cpoFechaUltMod.equals(that.cpoFechaUltMod) : that.cpoFechaUltMod != null)
            return false;
        if (cpoUsuarioUltMod != null ? !cpoUsuarioUltMod.equals(that.cpoUsuarioUltMod) : that.cpoUsuarioUltMod != null)
            return false;
        if (cpoFechaBaja != null ? !cpoFechaBaja.equals(that.cpoFechaBaja) : that.cpoFechaBaja != null) return false;
        if (cpoUsuarioBaja != null ? !cpoUsuarioBaja.equals(that.cpoUsuarioBaja) : that.cpoUsuarioBaja != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cpoId;
        result = 31 * result + cpoCantMetros;
        result = 31 * result + (cpoCoordenadas != null ? cpoCoordenadas.hashCode() : 0);
        result = 31 * result + (cpoFechaAlta != null ? cpoFechaAlta.hashCode() : 0);
        result = 31 * result + (cpoFechaBaja != null ? cpoFechaBaja.hashCode() : 0);
        result = 31 * result + (cpoFechaUltMod != null ? cpoFechaUltMod.hashCode() : 0);
        result = 31 * result + (cpoNombre != null ? cpoNombre.hashCode() : 0);
        result = 31 * result + (cpoObservaciones != null ? cpoObservaciones.hashCode() : 0);
        result = 31 * result + (cpoPropietario != null ? cpoPropietario.hashCode() : 0);
        result = 31 * result + (cpoUbicacion != null ? cpoUbicacion.hashCode() : 0);
        result = 31 * result + (cpoUsuarioAlta != null ? cpoUsuarioAlta.hashCode() : 0);
        result = 31 * result + (cpoUsuarioBaja != null ? cpoUsuarioBaja.hashCode() : 0);
        result = 31 * result + (cpoUsuarioUltMod != null ? cpoUsuarioUltMod.hashCode() : 0);
        return result;
    }
}
