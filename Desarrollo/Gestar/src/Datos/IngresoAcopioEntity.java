package Datos;

import Granos.TipoGrano;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by OWNER on 7/3/2016.
 */
@Entity
@Table(name = "ingreso_acopio", schema = "", catalog = "gestar")
public class IngresoAcopioEntity {


    private Integer ingresoId;
    private Date ingresoFecha;
    private Integer ingresoCantidadTotal;
    private String estadoSemilla;
    private String descripcion;

    private Date ingresoFechaAlta;
    private String ingresoUsuarioAlta;
    private Date ingresoFechaUltMod;
    private String ingresoUsuarioUltMod;
    private Date ingresoFechaBaja;
    private String ingresoUsuarioBaja;

//    private List<DetalleIngresoAcopioEntity> listaDetalleAcopioEntity;

    private AcopioEntity acopio;
    private TipoAcopioEntity tipoAcopio;
    private TipoLaboreoEntity tipoLaboreo;
    private LoteEntity lote;
    private CampaniaEntity campania;
    private TipoGranoEntity tipoGrano;
    private EmpresaEntity empresa;
    private ClienteEntity cliente;

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
    @Column(name = "ingreso_fecha")
    public Date getIngresoFecha() {
        return ingresoFecha;
    }

    public void setIngresoFecha(Date ingresoFecha) {
        this.ingresoFecha = ingresoFecha;
    }
    @Column(name = "ingreso_cantidad_total")
    public Integer getIngresoCantidadTotal() {
        return ingresoCantidadTotal;
    }

    public void setIngresoCantidadTotal(Integer ingresoCantidadTotal) {
        this.ingresoCantidadTotal = ingresoCantidadTotal;
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

    @ManyToOne
    @JoinColumn(name = "tipo_acopio_id", referencedColumnName = "tipo_acopio_id")
    public TipoAcopioEntity getTipoAcopio() {
        return tipoAcopio;
    }

    public void setTipoAcopio(TipoAcopioEntity tipoAcopio) {
        this.tipoAcopio = tipoAcopio;
    }

    @ManyToOne
    @JoinColumn(name = "lote_id", referencedColumnName = "lte_id")
    public LoteEntity getLote() {
        return lote;
    }

    public void setLote(LoteEntity lote) {
        this.lote = lote;
    }
    @ManyToOne
    @JoinColumn(name = "campania_id", referencedColumnName = "cna_id")
    public CampaniaEntity getCampania() {
        return campania;
    }

    public void setCampania(CampaniaEntity campania) {
        this.campania = campania;
    }
    @ManyToOne
    @JoinColumn(name = "tipo_grano_id", referencedColumnName = "tgr_id")
    public TipoGranoEntity getTipoGrano() {
        return tipoGrano;
    }

    public void setTipoGrano(TipoGranoEntity tipoGrano) {
        this.tipoGrano = tipoGrano;
    }

    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "empresa_id")
    public EmpresaEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaEntity empresa) {
        this.empresa = empresa;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_laboreo_id", referencedColumnName = "tpo_id")
    public TipoLaboreoEntity getTipoLaboreo() {
        return tipoLaboreo;
    }

    public void setTipoLaboreo(TipoLaboreoEntity tipoLaboreo) {
        this.tipoLaboreo = tipoLaboreo;
    }

    @ManyToOne
    @JoinColumn(name = "acopio_id", referencedColumnName = "acopio_id")
    public AcopioEntity getAcopio() {
        return acopio;
    }

    public void setAcopio(AcopioEntity acopio) {
        this.acopio = acopio;
    }

    @Column(name = "ingreso_estado")
    public String getEstadoSemilla() {
        return estadoSemilla;
    }

    public void setEstadoSemilla(String estadoSemilla) {
        this.estadoSemilla = estadoSemilla;
    }


    @Column(name = "ingreso_descripcion")

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
