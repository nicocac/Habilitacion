package Datos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Created by OWNER on 7/3/2016.
 */
@Entity
@Table(name = "solicitud_insumo", schema = "", catalog = "gestar")
public class SolicitudInsumoEntity {


    private Integer siId;
    private Integer    siNroSolicitud;
    private Integer    siNroRemito;
    private Date       siFechaSolicitud;
    private BigDecimal siMontoTotal;
    private Integer    siCantidadItems;
    private String     siEstado;

    private Date   siFechaAlta;
    private String siUsuarioAlta;
    private Date   siFechaUltMod;
    private String siUsuarioUltMod;
    private Date   siFechaBaja;
    private String siUsuarioBaja;


    private List<DetalleSolicitudInsumoEntity> detallesSolicitudInsumo;



    //======================================================================================
    @Id
    @GeneratedValue
    @Column(name = "si_id")
    public Integer getSiId() {
        return siId;
    }
    public void setSiId(Integer siId) {
        this.siId = siId;
    }

    @Column(name = "si_nro_solicitud")
    public Integer getSiNroSolicitud() {
        return siNroSolicitud;
    }
    public void setSiNroSolicitud(Integer siNroSolicitud) {
        this.siNroSolicitud = siNroSolicitud;
    }

    @Column(name = "si_nro_remito")
    public Integer getSiNroRemito() {
        return siNroRemito;
    }
    public void setSiNroRemito(Integer siNroRemito) {
        this.siNroRemito = siNroRemito;
    }

    @Basic
    @Column(name = "si_fecha_compra")
    public Date getSiFechaSolicitud() {
        return siFechaSolicitud;
    }
    public void setSiFechaSolicitud(Date siFechaSolicitud) {
        this.siFechaSolicitud = siFechaSolicitud;
    }

    @Basic
    @Column(name = "si_monto_total")
    public BigDecimal getSiMontoTotal() {
        return siMontoTotal;
    }
    public void setSiMontoTotal(BigDecimal siMontoTotal) {
        this.siMontoTotal = siMontoTotal;
    }

    @Basic
    @Column(name = "si_cantidad_items")
    public Integer getSiCantidadItems() {
        return siCantidadItems;
    }
    public void setSiCantidadItems(Integer siCantidadItems) {
        this.siCantidadItems = siCantidadItems;
    }

    @Column(name = "si_estado")
    public String getSiEstado() {
        return siEstado;
    }
    public void setSiEstado(String siEstado) {
        this.siEstado = siEstado;
    }
    @Basic
    @Column(name = "si_fecha_alta")
    public Date getSiFechaAlta() {
        return siFechaAlta;
    }
    public void setSiFechaAlta(Date siFechaAlta) {
        this.siFechaAlta = siFechaAlta;
    }

    @Basic
    @Column(name = "si_usuario_alta")
    public String getSiUsuarioAlta() {
        return siUsuarioAlta;
    }
    public void setSiUsuarioAlta(String siUsuarioAlta) {
        this.siUsuarioAlta = siUsuarioAlta;
    }

    @Basic
    @Column(name = "si_fecha_ult_mod")
    public Date getSiFechaUltMod() {
        return siFechaUltMod;
    }
    public void setSiFechaUltMod(Date siFechaUltMod) {
        this.siFechaUltMod = siFechaUltMod;
    }

    @Basic
    @Column(name = "si_usuario_ult_mod")
    public String getSiUsuarioUltMod() {
        return siUsuarioUltMod;
    }
    public void setSiUsuarioUltMod(String siUsuarioUltMod) {
        this.siUsuarioUltMod = siUsuarioUltMod;
    }

    @Basic
    @Column(name = "si_fecha_baja")
    public Date getSiFechaBaja() {
        return siFechaBaja;
    }
    public void setSiFechaBaja(Date siFechaBaja) {
        this.siFechaBaja = siFechaBaja;
    }

    @Basic
    @Column(name = "si_usuario_baja")
    public String getSiUsuarioBaja() {
        return siUsuarioBaja;
    }
    public void setSiUsuarioBaja(String siUsuarioBaja) {
        this.siUsuarioBaja = siUsuarioBaja;
    }

    @OneToMany(mappedBy = "solicitudInsumo")
    public List<DetalleSolicitudInsumoEntity> getDetallesSolicitudInsumo() {
        return detallesSolicitudInsumo;
    }

    public void setDetallesSolicitudInsumo(List<DetalleSolicitudInsumoEntity> detallesSolicitudInsumo) {
        this.detallesSolicitudInsumo = detallesSolicitudInsumo;
    }




    //=================================================================



}
