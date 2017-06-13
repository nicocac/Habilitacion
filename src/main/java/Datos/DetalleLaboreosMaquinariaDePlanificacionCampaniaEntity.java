package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "detalle_laboreos_maquinaria_de_planificacion_campania")
public class DetalleLaboreosMaquinariaDePlanificacionCampaniaEntity {


    private Integer id;
    private String observaciones;
    private int cantidadMaquinaria;

    private Date fechaAlta;
    private String usuarioAlta;
    private Date fechaUltMod;
    private String usuarioUltMod;
    private Date fechaBaja;
    private String usuarioBaja;

    private  DetallePlanificacionCampaniaLaboreosEntity detallePlanificacionCampaniaLaboreos;
    private MaquinariaEntity maquinaria;


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
    @JoinColumn(name = "maquinaria_id", referencedColumnName = "maq_id")
    public MaquinariaEntity getMaquinaria() {
        return maquinaria;
    }

    public void setMaquinaria(MaquinariaEntity maquinaria) {
        this.maquinaria = maquinaria;
    }


    @ManyToOne
    @JoinColumn(name = "detalle_planificacion_campania_laboreos_id", referencedColumnName = "id")

    public DetallePlanificacionCampaniaLaboreosEntity getDetallePlanificacionCampaniaLaboreos() {
        return detallePlanificacionCampaniaLaboreos;
    }

    public void setDetallePlanificacionCampaniaLaboreos(DetallePlanificacionCampaniaLaboreosEntity detallePlanificacionCampaniaLaboreos) {
        this.detallePlanificacionCampaniaLaboreos = detallePlanificacionCampaniaLaboreos;
    }

    //=================================================================


}
