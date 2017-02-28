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
    private String nombre;
    private Integer codigo;
    private Integer cantidadGrano;
    private Integer cantidadSoportada;

    private Date   acopioFechaAlta;
    private String acopioUsuarioAlta;
    private Date   acopioFechaUltMod;
    private String acopioUsuarioUltMod;
    private Date   acopioFechaBaja;
    private String acopioUsuarioBaja;


    private TipoAcopioEntity tipoAcopioEntity;
    private TipoEstadoGranoEntity tipoEstadoGrano;
    private String estadoGrano;
    private TipoGranoEntity tipoGrano;
    //======================================================================================


    @Id
    @GeneratedValue
    @Column(name="acopio_id")
    public Integer getAcopioId() {
        return acopioId;
    }

    @Column(name="acopio_nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name="acopio_codigo")
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setAcopioId(Integer acopioId) {
        this.acopioId = acopioId;
    }

    @Column(name="acopio_cantidad_soportada")
    public Integer getCantidadSoportada() {
        return cantidadSoportada;
    }

    public void setCantidadSoportada(Integer cantidadSoportada) {
        this.cantidadSoportada = cantidadSoportada;
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

    @Column(name="acopio_estado_grano")

    public String getEstadoGrano() {
        return estadoGrano;
    }

    public void setEstadoGrano(String estadoGrano) {
        this.estadoGrano = estadoGrano;
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


    @ManyToOne
    @JoinColumn(name = "tipo_grano_id", referencedColumnName = "tgr_id")
    public TipoGranoEntity getTipoGrano() {
        return tipoGrano;
    }

    public void setTipoGrano(TipoGranoEntity tipoGrano) {
        this.tipoGrano = tipoGrano;
    }
}
