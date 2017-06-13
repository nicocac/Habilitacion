package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;


@Entity
@Table(name = "tipo_estado_grano")
public class TipoEstadoGranoEntity {

    private Integer teGranoId;
    private String teGranoNombre;
    private String teGranoDescripcion;

    private Date   teGranoFechaAlta;
    private String teGranoUsuarioAlta;
    private Date   teGranoFechaUltMod;
    private String teGranoUsuarioUtlMod;
    private Date   teGranoFechaBaja;
    private String teGranoUsuarioBaja;


    //=================================================================
    @Id
    @GeneratedValue
    @Column(name = "te_grano_id")

    public Integer getTeGranoId() {
        return teGranoId;
    }

    public void setTeGranoId(Integer teGranoId) {
        this.teGranoId = teGranoId;
    }
    @Basic
    @Column(name = "te_grano_nombre")
    public String getTeGranoNombre() {
        return teGranoNombre;
    }

    public void setTeGranoNombre(String teGranoNombre) {
        this.teGranoNombre = teGranoNombre;
    }

    @Basic
    @Column(name = "te_grano_descripcion")
    public String getTeGranoDescripcion() {
        return teGranoDescripcion;
    }

    public void setTeGranoDescripcion(String teGranoDescripcion) {
        this.teGranoDescripcion = teGranoDescripcion;
    }
    @Basic
    @Column(name = "te_grano_fecha_alta")
    public Date getTeGranoFechaAlta() {
        return teGranoFechaAlta;
    }

    public void setTeGranoFechaAlta(Date teGranoFechaAlta) {
        this.teGranoFechaAlta = teGranoFechaAlta;
    }
    @Basic
    @Column(name = "te_grano_usuario_alta")
    public String getTeGranoUsuarioAlta() {
        return teGranoUsuarioAlta;
    }

    public void setTeGranoUsuarioAlta(String teGranoUsuarioAlta) {
        this.teGranoUsuarioAlta = teGranoUsuarioAlta;
    }
    @Basic
    @Column(name = "te_grano_fecha_ult_mod")
    public Date getTeGranoFechaUltMod() {
        return teGranoFechaUltMod;
    }

    public void setTeGranoFechaUltMod(Date teGranoFechaUltMod) {
        this.teGranoFechaUltMod = teGranoFechaUltMod;
    }
    @Basic
    @Column(name = "te_grano_usuario_utl_mod")
    public String getTeGranoUsuarioUtlMod() {
        return teGranoUsuarioUtlMod;
    }

    public void setTeGranoUsuarioUtlMod(String teGranoUsuarioUtlMod) {
        this.teGranoUsuarioUtlMod = teGranoUsuarioUtlMod;
    }
    @Basic
    @Column(name = "te_grano_fecha_baja")
    public Date getTeGranoFechaBaja() {
        return teGranoFechaBaja;
    }

    public void setTeGranoFechaBaja(Date teGranoFechaBaja) {
        this.teGranoFechaBaja = teGranoFechaBaja;
    }
    @Basic
    @Column(name = "te_grano_usuario_baja")
    public String getTeGranoUsuarioBaja() {
        return teGranoUsuarioBaja;
    }

    public void setTeGranoUsuarioBaja(String teGranoUsuarioBaja) {
        this.teGranoUsuarioBaja = teGranoUsuarioBaja;
    }


    //=================================================================


    @Override
    public String toString() {
        return teGranoNombre;
    }
}
