package Datos;

import Granos.TipoGrano;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "laboreo_ins_maq")
public class LaboreoInsumoMaqEntity {

    private Integer id;
    private InsumoEntity insumo;
    private MaquinariaEntity maquinaria;
    private TipoGranoEntity tipoGrano;
    private Integer tiempo;
    private Integer cantidadInsumo;
    private Integer cantidadMaquinaria;
    private Integer metricaPredefinida;
    private Integer descripcion;

    private Date fechaAlta;
    private String usuarioAlta;
    private Date fechaUltMod;
    private String usuarioUltMod;
    private Date fechaBaja;
    private String usuarioBaja;


  //===============================================================




    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "metrica_predefinida")
    public Integer getMetricaPredefinida() {
        return metricaPredefinida;
    }

    public void setMetricaPredefinida(Integer metricaPredefinida) {
        this.metricaPredefinida = metricaPredefinida;
    }

    @Basic
    @Column(name = "descripcion")
    public Integer getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Integer descripcion) {
        this.descripcion = descripcion;
    }

    @ManyToOne
    @JoinColumn(name = "insumo_id", referencedColumnName = "ins_id")
    public InsumoEntity getInsumo() {
        return insumo;
    }

    public void setInsumo(InsumoEntity insumo) {
        this.insumo = insumo;
    }

    @ManyToOne
    @JoinColumn(name = "maquinaria_id", referencedColumnName = "maq_id")
    public MaquinariaEntity getMaquinaria() {
        return maquinaria;
    }

    public void setMaquinaria(MaquinariaEntity maquinaria) {
        this.maquinaria = maquinaria;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_grano_id", referencedColumnName = "tgr_id")
    public TipoGranoEntity getTipoGrano() {
        return tipoGrano;
    }

    public void setTipoGrano(TipoGranoEntity tipoGrano) {
        this.tipoGrano = tipoGrano;
    }

    @Basic
    @Column(name = "tiempo")
    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    @Basic
    @Column(name = "cantidad_insumo")
    public Integer getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(Integer cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
    }

    @Basic
    @Column(name = "cantidad_maquinaria")
    public Integer getCantidadMaquinaria() {
        return cantidadMaquinaria;
    }

    public void setCantidadMaquinaria(Integer cantidadMaquinaria) {
        this.cantidadMaquinaria = cantidadMaquinaria;
    }

    @Basic
    @Column(name = "fecha_alta")
    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Basic
    @Column(name = "usuario_alta")
    public String getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    @Basic
    @Column(name = "fecha_ult_mod")
    public Date getFechaUltMod() {
        return fechaUltMod;
    }

    public void setFechaUltMod(Date fechaUltMod) {
        this.fechaUltMod = fechaUltMod;
    }

    @Basic
    @Column(name = "usuario_ult_mod")
    public String getUsuarioUltMod() {
        return usuarioUltMod;
    }

    public void setUsuarioUltMod(String usuarioUltMod) {
        this.usuarioUltMod = usuarioUltMod;
    }

    @Basic
    @Column(name = "fecha_baja")
    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @Basic
    @Column(name = "usuario_baja")
    public String getUsuarioBaja() {
        return usuarioBaja;
    }

    public void setUsuarioBaja(String usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }




}
