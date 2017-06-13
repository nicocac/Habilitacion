package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by OWNER on 7/3/2016.
 */
@Entity
@Table(name = "ingreso_insumo")
public class IngresoInsumoEntity {


    private Integer ingresoId;
    private Integer ingresoNroRemito;
    private Date ingresoFecha;
    private Integer ingresoCantidadItems;

    private Date ingresoFechaAlta;
    private String ingresoUsuarioAlta;
    private Date ingresoFechaUltMod;
    private String ingresoUsuarioUltMod;
    private Date ingresoFechaBaja;
    private String ingresoUsuarioBaja;

    private List<DetalleIngresoInsumoEntity> listaDetalleIngresoInsumoEntity;


    //======================================================================================

    @Id
    @GeneratedValue
    @Column(name = "ingreso_id")
    public Integer getIngresoId() {
        return ingresoId;
    }

    public void setIngresoId(Integer ingresoId) {
        this.ingresoId = ingresoId;
    }

    @Column(name = "ingreso_nro_remito")
    public Integer getIngresoNroRemito() {
        return ingresoNroRemito;
    }

    public void setIngresoNroRemito(Integer ingresoNroRemito) {
        this.ingresoNroRemito = ingresoNroRemito;
    }

    @Column(name = "ingreso_fecha")
    public Date getIngresoFecha() {
        return ingresoFecha;
    }

    public void setIngresoFecha(Date ingresoFecha) {
        this.ingresoFecha = ingresoFecha;
    }

    @Column(name = "ingreso_cantidad_items")
    public Integer getIngresoCantidadItems() {
        return ingresoCantidadItems;
    }

    public void setIngresoCantidadItems(Integer ingresoCantidadItems) {
        this.ingresoCantidadItems = ingresoCantidadItems;
    }

    @Column(name = "ingreso_fecha_alta")
    public Date getIngresoFechaAlta() {
        return ingresoFechaAlta;
    }

    public void setIngresoFechaAlta(Date ingresoFechaAlta) {
        this.ingresoFechaAlta = ingresoFechaAlta;
    }

    @Column(name = "ingreso_usuario_alta")
    public String getIngresoUsuarioAlta() {
        return ingresoUsuarioAlta;
    }

    public void setIngresoUsuarioAlta(String ingresoUsuarioAlta) {
        this.ingresoUsuarioAlta = ingresoUsuarioAlta;
    }

    @Column(name = "ingreso_fecha_ult_mod")
    public Date getIngresoFechaUltMod() {
        return ingresoFechaUltMod;
    }

    public void setIngresoFechaUltMod(Date ingresoFechaUltMod) {
        this.ingresoFechaUltMod = ingresoFechaUltMod;
    }

    @Column(name = "ingreso_usuario_ult_mod")
    public String getIngresoUsuarioUltMod() {
        return ingresoUsuarioUltMod;
    }

    public void setIngresoUsuarioUltMod(String ingresoUsuarioUltMod) {
        this.ingresoUsuarioUltMod = ingresoUsuarioUltMod;
    }

    @Column(name = "ingreso_fecha_baja")
    public Date getIngresoFechaBaja() {
        return ingresoFechaBaja;
    }

    public void setIngresoFechaBaja(Date ingresoFechaBaja) {
        this.ingresoFechaBaja = ingresoFechaBaja;
    }

    @Column(name = "ingreso_usuario_baja")
    public String getIngresoUsuarioBaja() {
        return ingresoUsuarioBaja;
    }

    public void setIngresoUsuarioBaja(String ingresoUsuarioBaja) {
        this.ingresoUsuarioBaja = ingresoUsuarioBaja;
    }

    @OneToMany(mappedBy = "ingresoInsumo")
    public List<DetalleIngresoInsumoEntity> getListaDetalleIngresoInsumoEntity() {
        return listaDetalleIngresoInsumoEntity;
    }

    public void setListaDetalleIngresoInsumoEntity(List<DetalleIngresoInsumoEntity> listaDetalleIngresoInsumoEntity) {
        this.listaDetalleIngresoInsumoEntity = listaDetalleIngresoInsumoEntity;
    }


}
