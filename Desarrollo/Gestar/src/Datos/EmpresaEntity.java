package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "empresa", schema = "", catalog = "gestar")
public class EmpresaEntity {

    private Integer empresaId;
    private String  empresaNombre;
    private String  empresaDescripcion;

    private Date   empresaFechaAlta;
    private String empresaUsuarioAlta;
    private Date   empresaFechaUltMod;
    private String empresaUsuarioUltMod;
    private Date   empresaFechaBaja;
    private String empresaUsuarioBaja;

    //=================================================================
    @Id
    @GeneratedValue
    @Column(name = "empresa_id")
    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    @Column(name = "empresa_nombre")
    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    @Column(name = "empresa_descripcion")
    public String getEmpresaDescripcion() {
        return empresaDescripcion;
    }

    public void setEmpresaDescripcion(String empresaDescripcion) {
        this.empresaDescripcion = empresaDescripcion;
    }

    @Column(name = "empresa_fecha_alta")
    public Date getEmpresaFechaAlta() {
        return empresaFechaAlta;
    }

    public void setEmpresaFechaAlta(Date empresaFechaAlta) {
        this.empresaFechaAlta = empresaFechaAlta;
    }

    @Column(name = "empresa_usuario_alta")
    public String getEmpresaUsuarioAlta() {
        return empresaUsuarioAlta;
    }

    public void setEmpresaUsuarioAlta(String empresaUsuarioAlta) {
        this.empresaUsuarioAlta = empresaUsuarioAlta;
    }

    @Column(name = "empresa_fecha_ult_mod")
    public Date getEmpresaFechaUltMod() {
        return empresaFechaUltMod;
    }

    public void setEmpresaFechaUltMod(Date empresaFechaUltMod) {
        this.empresaFechaUltMod = empresaFechaUltMod;
    }

    @Column(name = "empresa_usuario_ult_mod")
    public String getEmpresaUsuarioUltMod() {
        return empresaUsuarioUltMod;
    }

    public void setEmpresaUsuarioUltMod(String empresaUsuarioUltMod) {
        this.empresaUsuarioUltMod = empresaUsuarioUltMod;
    }

    @Column(name = "empresa_fecha_baja")
    public Date getEmpresaFechaBaja() {
        return empresaFechaBaja;
    }

    public void setEmpresaFechaBaja(Date empresaFechaBaja) {
        this.empresaFechaBaja = empresaFechaBaja;
    }

    @Column(name = "empresa_usuario_baja")
    public String getEmpresaUsuarioBaja() {
        return empresaUsuarioBaja;
    }

    public void setEmpresaUsuarioBaja(String empresaUsuarioBaja) {
        this.empresaUsuarioBaja = empresaUsuarioBaja;
    }



    //=================================================================


    @Override
    public String toString() {
        return empresaNombre;
    }
}

