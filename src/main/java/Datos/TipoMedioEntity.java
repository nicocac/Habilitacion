package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by OWNER on 5/30/2016.
 */
@Entity
@Table(name = "tipo_medio")
public class TipoMedioEntity {

    private Integer tiMedioId;
    private String tiMedioNombre;
    private String tiMedioDescripcion;
    private Date tiMedioFechaAlta;
    private String tiMedioUsuarioAlta;
    private Date tiMedioFechaUltMod;
    private String tiMedioUsuarioUtlMod;
    private Date tiMedioFechaBaja;
    private String tiMedioUsuarioBaja;



    //=================================================================

    @Id
    @GeneratedValue
    @Column(name = "ti_medio_id")
    public Integer getTiMedioId() {
        return tiMedioId;
    }

    public void setTiMedioId(Integer tiMedioId) {
        this.tiMedioId = tiMedioId;
    }

    @Column(name = "ti_medio_nombre")
    public String getTiMedioNombre() {
        return tiMedioNombre;
    }

    public void setTiMedioNombre(String tiMedioNombre) {
        this.tiMedioNombre = tiMedioNombre;
    }

    @Column(name = "ti_medio_descripcion")
    public String getTiMedioDescripcion() {
        return tiMedioDescripcion;
    }

    public void setTiMedioDescripcion(String tiMedioDescripcion) {
        this.tiMedioDescripcion = tiMedioDescripcion;
    }

    @Column(name = "ti_medio_fecha_alta")
    public Date getTiMedioFechaAlta() {
        return tiMedioFechaAlta;
    }

    public void setTiMedioFechaAlta(Date tiMedioFechaAlta) {
        this.tiMedioFechaAlta = tiMedioFechaAlta;
    }

    @Column(name = "ti_medio_usuario_alta")
    public String getTiMedioUsuarioAlta() {
        return tiMedioUsuarioAlta;
    }

    public void setTiMedioUsuarioAlta(String tiMedioUsuarioAlta) {
        this.tiMedioUsuarioAlta = tiMedioUsuarioAlta;
    }

    @Column(name = "ti_medio_fecha_ult_mod")
    public Date getTiMedioFechaUltMod() {
        return tiMedioFechaUltMod;
    }

    public void setTiMedioFechaUltMod(Date tiMedioFechaUltMod) {
        this.tiMedioFechaUltMod = tiMedioFechaUltMod;
    }

    @Column(name = "ti_medio_usuario_utl_mod")
    public String getTiMedioUsuarioUtlMod() {
        return tiMedioUsuarioUtlMod;
    }

    public void setTiMedioUsuarioUtlMod(String tiMedioUsuarioUtlMod) {
        this.tiMedioUsuarioUtlMod = tiMedioUsuarioUtlMod;
    }

    @Column(name = "ti_medio_fecha_baja")
    public Date getTiMedioFechaBaja() {
        return tiMedioFechaBaja;
    }

    public void setTiMedioFechaBaja(Date tiMedioFechaBaja) {
        this.tiMedioFechaBaja = tiMedioFechaBaja;
    }

    @Column(name = "ti_medio_usuario_baja")
    public String getTiMedioUsuarioBaja() {
        return tiMedioUsuarioBaja;
    }

    public void setTiMedioUsuarioBaja(String tiMedioUsuarioBaja) {
        this.tiMedioUsuarioBaja = tiMedioUsuarioBaja;
    }







    //=================================================================


    @Override
    public String toString() {
        return tiMedioNombre;
    }

    //=================================================================



}
