package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "detalle_insumos_labore_planificacion_campania", schema = "", catalog = "gestar")
public class DetalleLaboreosInsumoDePlanificacionCampaniaEntity {


    private Integer id;
    private String observaciones;
    private int cantidadInsumo;
    private int cantidadMaquinaria;

    private Date fechaAlta;
    private String usuarioAlta;
    private Date fechaUltMod;
    private String usuarioUltMod;
    private Date fechaBaja;
    private String usuarioBaja;

    private  PlanificacionCampaniaEntity planificacion;
    private LaboreoEntity laboreo;
    private InsumoEntity insumo;

//    private MaquinariaEntity maquinaria;
    private TipoGranoEntity tipoGrano;
    private LoteCampaniaEntity loteCampania;

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
    @Column(name = "observaciones")
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Basic
    @Column(name = "cantidad_insumo")
    public int getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(int cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
    }

    @Basic
    @Column(name = "cantidad_maquinaria")
    public int getCantidadMaquinaria() {
        return cantidadMaquinaria;
    }

    public void setCantidadMaquinaria(int cantidadMaquinaria) {
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

    @ManyToOne
    @JoinColumn(name = "insumo_id", referencedColumnName = "ins_id")
    public InsumoEntity getInsumo() {
        return insumo;
    }

    public void setInsumo(InsumoEntity insumo) {
        this.insumo = insumo;
    }

//    @ManyToOne
//    @JoinColumn(name = "maquinaria_id", referencedColumnName = "maq_id")
//    public MaquinariaEntity getMaquinaria() {
//        return maquinaria;
//    }
//
//    public void setMaquinaria(MaquinariaEntity maquinaria) {
//        this.maquinaria = maquinaria;
//    }

    @ManyToOne
    @JoinColumn(name = "laboreo_id", referencedColumnName = "lbo_id", nullable = false)
    public LaboreoEntity getLaboreo() {
        return laboreo;
    }

    public void setLaboreo(LaboreoEntity laboreo) {
        this.laboreo = laboreo;
    }

    @ManyToOne
    @JoinColumn(name = "lote_campania_id", referencedColumnName = "lcp_id")
    public LoteCampaniaEntity getLoteCampania() {
        return loteCampania;
    }

    public void setLoteCampania(LoteCampaniaEntity loteCampania) {
        this.loteCampania = loteCampania;
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
    @JoinColumn(name = "planificacion_campania_id", referencedColumnName = "planificacion_id")
    public PlanificacionCampaniaEntity getPlanificacion() {
        return planificacion;
    }

    public void setPlanificacion(PlanificacionCampaniaEntity planificacion) {
        this.planificacion = planificacion;
    }

    //=================================================================


}
