package Datos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "transporte", schema = "", catalog = "gestar")
public class TransporteEntity {

    private Integer transporteId;
    private String  transporteNombre;
    private String  transporteDescripcion;

    private Date   transporteFechaAlta;
    private String transporteUsuarioAlta;
    private Date   transporteFechaUltMod;
    private String transporteUsuarioUltMod;
    private Date   transporteFechaBaja;
    private String transporteUsuarioBaja;

    //=================================================================

    @Id
    @GeneratedValue
    @Column(name = "transporte_id")
    public Integer getTransporteId() {
        return transporteId;
    }

    public void setTransporteId(Integer transporteId) {
        this.transporteId = transporteId;
    }

    @Column(name = "transporte_nombre")
    public String getTransporteNombre() {
        return transporteNombre;
    }

    public void setTransporteNombre(String transporteNombre) {
        this.transporteNombre = transporteNombre;
    }

    @Column(name = "transporte_descripcion")
    public String getTransporteDescripcion() {
        return transporteDescripcion;
    }

    public void setTransporteDescripcion(String transporteDescripcion) {
        this.transporteDescripcion = transporteDescripcion;
    }

    @Column(name = "transporte_fecha_alta")
    public Date getTransporteFechaAlta() {
        return transporteFechaAlta;
    }

    public void setTransporteFechaAlta(Date transporteFechaAlta) {
        this.transporteFechaAlta = transporteFechaAlta;
    }

    @Column(name = "transporte_usuario_alta")
    public String getTransporteUsuarioAlta() {
        return transporteUsuarioAlta;
    }

    public void setTransporteUsuarioAlta(String transporteUsuarioAlta) {
        this.transporteUsuarioAlta = transporteUsuarioAlta;
    }

    @Column(name = "transporte_fecha_ult_mod")
    public Date getTransporteFechaUltMod() {
        return transporteFechaUltMod;
    }

    public void setTransporteFechaUltMod(Date transporteFechaUltMod) {
        this.transporteFechaUltMod = transporteFechaUltMod;
    }

    @Column(name = "transporte_usuario_ult_mod")
    public String getTransporteUsuarioUltMod() {
        return transporteUsuarioUltMod;
    }

    public void setTransporteUsuarioUltMod(String transporteUsuarioUltMod) {
        this.transporteUsuarioUltMod = transporteUsuarioUltMod;
    }

    @Column(name = "transporte_fecha_baja")
    public Date getTransporteFechaBaja() {
        return transporteFechaBaja;
    }

    public void setTransporteFechaBaja(Date transporteFechaBaja) {
        this.transporteFechaBaja = transporteFechaBaja;
    }

    @Column(name = "transporte_usuario_baja")
    public String getTransporteUsuarioBaja() {
        return transporteUsuarioBaja;
    }

    public void setTransporteUsuarioBaja(String transporteUsuarioBaja) {
        this.transporteUsuarioBaja = transporteUsuarioBaja;
    }


    //=================================================================


    @Override
    public String toString() {
        return transporteNombre;
    }
}

