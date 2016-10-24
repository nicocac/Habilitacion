package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by OWNER on 7/3/2016.
 */
@Entity
@Table(name = "acopio", schema = "", catalog = "gestar")
public class AcopioEntity {


    private Integer acopioId;
    private Integer cantidadGrano;

    private Date   acopioFechaAlta;
    private String acopioUsuarioAlta;
    private Date   acopioFechaUltMod;
    private String acopioUsuarioUltMod;
    private Date   acopioFechaBaja;
    private String acopioUsuarioBaja;


    private TipoAcopioEntity tipoAcopioEntity;
    private TipoEstadoGranoEntity tipoEstadoGrano;
    //======================================================================================


    @Id
    @GeneratedValue
    @Column(name="acopio_id")
    public Integer getAcopioId() {
        return acopioId;
    }

    public void setAcopioId(Integer acopioId) {
        this.acopioId = acopioId;
    }
    @Column(name="acopio_cantidad_grano")
    public Integer getCantidadGrano() {
        return cantidadGrano;
    }

    public void setCantidadGrano(Integer cantidadGrano) {
        this.cantidadGrano = cantidadGrano;
    }
    @Column(name="acopio_fecha_alta")
    public Date getAcopioFechaAlta() {
        return acopioFechaAlta;
    }

    public void setAcopioFechaAlta(Date acopioFechaAlta) {
        this.acopioFechaAlta = acopioFechaAlta;
    }
    @Column(name="acopio_usuario_alta")
    public String getAcopioUsuarioAlta() {
        return acopioUsuarioAlta;
    }

    public void setAcopioUsuarioAlta(String acopioUsuarioAlta) {
        this.acopioUsuarioAlta = acopioUsuarioAlta;
    }
    @Column(name="acopio_fecha_ult_mod")
    public Date getAcopioFechaUltMod() {
        return acopioFechaUltMod;
    }

    public void setAcopioFechaUltMod(Date acopioFechaUltMod) {
        this.acopioFechaUltMod = acopioFechaUltMod;
    }
    @Column(name="acopio_usuario_ult_mod")
    public String getAcopioUsuarioUltMod() {
        return acopioUsuarioUltMod;
    }

    public void setAcopioUsuarioUltMod(String acopioUsuarioUltMod) {
        this.acopioUsuarioUltMod = acopioUsuarioUltMod;
    }
    @Column(name="acopio_fecha_baja")
    public Date getAcopioFechaBaja() {
        return acopioFechaBaja;
    }

    public void setAcopioFechaBaja(Date acopioFechaBaja) {
        this.acopioFechaBaja = acopioFechaBaja;
    }
    @Column(name="acopio_usuario_baja")
    public String getAcopioUsuarioBaja() {
        return acopioUsuarioBaja;
    }

    public void setAcopioUsuarioBaja(String acopioUsuarioBaja) {
        this.acopioUsuarioBaja = acopioUsuarioBaja;
    }


    @ManyToOne
    @JoinColumn(name = "tipo_acopio_id", referencedColumnName = "tipo_acopio_id")
    public TipoAcopioEntity getTipoAcopioEntity() {
        return tipoAcopioEntity;
    }

    public void setTipoAcopioEntity(TipoAcopioEntity tipoAcopioEntity) {
        this.tipoAcopioEntity = tipoAcopioEntity;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_estado_grano_id", referencedColumnName = "te_grano_id")
    public TipoEstadoGranoEntity getTipoEstadoGrano() {
        return tipoEstadoGrano;
    }

    public void setTipoEstadoGrano(TipoEstadoGranoEntity tipoEstadoGrano) {
        this.tipoEstadoGrano = tipoEstadoGrano;
    }

    @Override
    public String toString() {
        return this.getAcopioId().toString();
    }
}
