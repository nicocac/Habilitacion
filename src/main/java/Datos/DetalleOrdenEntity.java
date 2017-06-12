package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "detalle_orden", schema = "", catalog = "gestar")
public class DetalleOrdenEntity {


    private Integer id;
    private String observaciones;
    private int cantidadInsumo;
    private int cantidadMaquinaria;
    private int cantidadHorasMaquinaria;

    private Date dboFechaAlta;
    private String dboUsuarioAlta;
    private Date dboFechaUltMod;
    private String dboUsuarioUltMod;
    private Date dboFechaBaja;
    private String dboUsuarioBaja;


    private InsumoEntity insumo;
    private MaquinariaEntity maquinaria;
    private OrdenTrabajoEntity orden;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "observaciones")
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Column(name = "cantidadInsumo")
    public int getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(int cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
    }

    @Column(name = "cantidadMaquinaria")
    public int getCantidadMaquinaria() {
        return cantidadMaquinaria;
    }

    public void setCantidadMaquinaria(int cantidadMaquinaria) {
        this.cantidadMaquinaria = cantidadMaquinaria;
    }

    @Column(name = "cantidadHorasMaquinaria")
    public int getCantidadHorasMaquinaria() {
        return cantidadHorasMaquinaria;
    }

    public void setCantidadHorasMaquinaria(int cantidadHorasMaquinaria) {
        this.cantidadHorasMaquinaria = cantidadHorasMaquinaria;
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
    @JoinColumn(name = "orden_id", referencedColumnName = "id")
    public OrdenTrabajoEntity getOrden() {
        return orden;
    }

    public void setOrden(OrdenTrabajoEntity orden) {
        this.orden = orden;
    }





    @Basic
    @Column(name = "dbo_fecha_alta")
    public Date getDboFechaAlta() {
        return dboFechaAlta;
    }

    public void setDboFechaAlta(Date dboFechaAlta) {
        this.dboFechaAlta = dboFechaAlta;
    }

    @Basic
    @Column(name = "dbo_usuario_alta")
    public String getDboUsuarioAlta() {
        return dboUsuarioAlta;
    }

    public void setDboUsuarioAlta(String dboUsuarioAlta) {
        this.dboUsuarioAlta = dboUsuarioAlta;
    }

    @Basic
    @Column(name = "dbo_fecha_ult_mod")
    public Date getDboFechaUltMod() {
        return dboFechaUltMod;
    }

    public void setDboFechaUltMod(Date dboFechaUltMod) {
        this.dboFechaUltMod = dboFechaUltMod;
    }

    @Basic
    @Column(name = "dbo_usuario_ult_mod")
    public String getDboUsuarioUltMod() {
        return dboUsuarioUltMod;
    }

    public void setDboUsuarioUltMod(String dboUsuarioUltMod) {
        this.dboUsuarioUltMod = dboUsuarioUltMod;
    }

    @Basic
    @Column(name = "dbo_fecha_baja")
    public Date getDboFechaBaja() {
        return dboFechaBaja;
    }

    public void setDboFechaBaja(Date dboFechaBaja) {
        this.dboFechaBaja = dboFechaBaja;
    }

    @Basic
    @Column(name = "dbo_usuario_baja")
    public String getDboUsuarioBaja() {
        return dboUsuarioBaja;
    }

    public void setDboUsuarioBaja(String dboUsuarioBaja) {
        this.dboUsuarioBaja = dboUsuarioBaja;
    }


}
