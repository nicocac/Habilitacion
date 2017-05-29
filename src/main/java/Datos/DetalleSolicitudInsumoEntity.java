package Datos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by OWNER on 7/3/2016.
 */
@Entity
@Table(name = "detalle_solicitud_insumo", schema = "", catalog = "gestar")
public class DetalleSolicitudInsumoEntity {


    private Integer dsiId;
    private BigDecimal dsiCantidad;
    private BigDecimal dsiPrecio;
    private String     dsiObservaciones;
    private Date       dsiFechaAlta;
    private String     dsiUsuarioAlta;
    private Date       dsiFechaUltMod;
    private String     dsiUsuarioUltMod;
    private Date       dsiFechaBaja;
    private String     dsiUsuarioBaja;

    private SolicitudInsumoEntity solicitudInsumo;
    private InsumoEntity insumo;

    //======================================================================================
    private Integer cpdCpaId;
    private Integer cpdInsId;

    //=================================================================

    @Id
    @GeneratedValue
    @Column(name = "dsi_id")
    public Integer getDsiId() {
        return dsiId;
    }

    public void setDsiId(Integer dsiId) {
        this.dsiId = dsiId;
    }
    @Basic
    @Column(name = "dsi_cantidad")

    public BigDecimal getDsiCantidad() {
        return dsiCantidad;
    }

    public void setDsiCantidad(BigDecimal dsiCantidad) {
        this.dsiCantidad = dsiCantidad;
    }
    @Basic
    @Column(name = "dsi_precio")

    public BigDecimal getDsiPrecio() {
        return dsiPrecio;
    }

    public void setDsiPrecio(BigDecimal dsiPrecio) {
        this.dsiPrecio = dsiPrecio;
    }
    @Basic
    @Column(name = "dsi_observaciones")

    public String getDsiObservaciones() {
        return dsiObservaciones;
    }

    public void setDsiObservaciones(String dsiObservaciones) {
        this.dsiObservaciones = dsiObservaciones;
    }
    @Basic
    @Column(name = "dsi_fecha_alta")

    public Date getDsiFechaAlta() {
        return dsiFechaAlta;
    }

    public void setDsiFechaAlta(Date dsiFechaAlta) {
        this.dsiFechaAlta = dsiFechaAlta;
    }
    @Basic
    @Column(name = "dsi_usuario_alta")

    public String getDsiUsuarioAlta() {
        return dsiUsuarioAlta;
    }

    public void setDsiUsuarioAlta(String dsiUsuarioAlta) {
        this.dsiUsuarioAlta = dsiUsuarioAlta;
    }
    @Basic
    @Column(name = "dsi_fecha_ult_mod")

    public Date getDsiFechaUltMod() {
        return dsiFechaUltMod;
    }

    public void setDsiFechaUltMod(Date dsiFechaUltMod) {
        this.dsiFechaUltMod = dsiFechaUltMod;
    }
    @Basic
    @Column(name = "dsi_usuario_ult_mod")

    public String getDsiUsuarioUltMod() {
        return dsiUsuarioUltMod;
    }

    public void setDsiUsuarioUltMod(String dsiUsuarioUltMod) {
        this.dsiUsuarioUltMod = dsiUsuarioUltMod;
    }

    @Basic
    @Column(name = "dsi_fecha_baja")

    public Date getDsiFechaBaja() {
        return dsiFechaBaja;
    }

    public void setDsiFechaBaja(Date dsiFechaBaja) {
        this.dsiFechaBaja = dsiFechaBaja;
    }
    @Basic
    @Column(name = "dsi_usuario_baja")
    public String getDsiUsuarioBaja() {
        return dsiUsuarioBaja;
    }

    public void setDsiUsuarioBaja(String dsiUsuarioBaja) {
        this.dsiUsuarioBaja = dsiUsuarioBaja;
    }

    @ManyToOne
    @JoinColumn(name = "solicitud_insumo_id", referencedColumnName = "si_id")
    public SolicitudInsumoEntity getSolicitudInsumo() {
        return solicitudInsumo;
    }

    public void setSolicitudInsumo(SolicitudInsumoEntity solicitudInsumo) {
        this.solicitudInsumo = solicitudInsumo;
    }

    @ManyToOne
    @JoinColumn(name = "insumo_id", referencedColumnName = "ins_id")
    public InsumoEntity getInsumo() {
        return insumo;
    }

    public void setInsumo(InsumoEntity insumo) {
        this.insumo = insumo;
    }







    //=================================================================


    //=================================================================

    public Integer getCpdCpaId() {
        return cpdCpaId;
    }

    public void setCpdCpaId(Integer cpdCpaId) {
        this.cpdCpaId = cpdCpaId;
    }

    public Integer getCpdInsId() {
        return cpdInsId;
    }

    public void setCpdInsId(Integer cpdInsId) {
        this.cpdInsId = cpdInsId;
    }
}
