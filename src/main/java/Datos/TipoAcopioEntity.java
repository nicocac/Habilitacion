package Datos;

import sun.util.resources.cldr.en.TimeZoneNames_en_AU;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;


@Entity
@Table(name = "tipo_acopio")
public class TipoAcopioEntity {

    private Integer tipoAcopioId;
    private String  tipoAcopioNombre;
    private String  tipoAcopioDescripcion;
    private Date    tipoAcopioFechaAlta;
    private String  tipoAcopioUsuarioAlta;
    private Date    tipoAcopioFechaUltMod;
    private String  tipoAcopioUsuarioUtlMod;
    private Date    tipoAcopioFechaBaja;
    private String  tipoAcopioUsuarioBaja;

    private Collection<IngresoAcopioEntity> ingresoAcopio;
    private TipoMedioEntity tipoMedio;
    //=================================================================
    @Id
    @GeneratedValue
    @Column(name = "tipo_acopio_id")

    public Integer getTipoAcopioId() {
        return tipoAcopioId;
    }

    public void setTipoAcopioId(Integer tipoAcopioId) {
        this.tipoAcopioId = tipoAcopioId;
    }

    @Basic
    @Column(name = "tipo_acopio_nombre")
    public String getTipoAcopioNombre() {
        return tipoAcopioNombre;
    }

    public void setTipoAcopioNombre(String tipoAcopioNombre) {
        this.tipoAcopioNombre = tipoAcopioNombre;
    }

    @Basic
    @Column(name = "tipo_acopio_descripcion")
    public String getTipoAcopioDescripcion() {
        return tipoAcopioDescripcion;
    }

    public void setTipoAcopioDescripcion(String tipoAcopioDescripcion) {
        this.tipoAcopioDescripcion = tipoAcopioDescripcion;
    }

    @Basic
    @Column(name = "tipo_acopio_fecha_alta")
    public Date getTipoAcopioFechaAlta() {
        return tipoAcopioFechaAlta;
    }

    public void setTipoAcopioFechaAlta(Date tipoAcopioFechaAlta) {
        this.tipoAcopioFechaAlta = tipoAcopioFechaAlta;
    }
    @Basic
    @Column(name = "tipo_acopio_usuario_alta")
    public String getTipoAcopioUsuarioAlta() {
        return tipoAcopioUsuarioAlta;
    }

    public void setTipoAcopioUsuarioAlta(String tipoAcopioUsuarioAlta) {
        this.tipoAcopioUsuarioAlta = tipoAcopioUsuarioAlta;
    }
    @Basic
    @Column(name = "tipo_acopio_fecha_ult_mod")
    public Date getTipoAcopioFechaUltMod() {
        return tipoAcopioFechaUltMod;
    }

    public void setTipoAcopioFechaUltMod(Date tipoAcopioFechaUltMod) {
        this.tipoAcopioFechaUltMod = tipoAcopioFechaUltMod;
    }
    @Basic
    @Column(name = "tipo_acopio__usuario_utl_mod")
    public String getTipoAcopioUsuarioUtlMod() {
        return tipoAcopioUsuarioUtlMod;
    }

    public void setTipoAcopioUsuarioUtlMod(String tipoAcopioUsuarioUtlMod) {
        this.tipoAcopioUsuarioUtlMod = tipoAcopioUsuarioUtlMod;
    }
    @Basic
    @Column(name = "tipo_acopio__fecha_baja")
    public Date getTipoAcopioFechaBaja() {
        return tipoAcopioFechaBaja;
    }

    public void setTipoAcopioFechaBaja(Date tipoAcopioFechaBaja) {
        this.tipoAcopioFechaBaja = tipoAcopioFechaBaja;
    }

    @Basic
    @Column(name = "tipo_acopio__usuario_baja")
    public String getTipoAcopioUsuarioBaja() {
        return tipoAcopioUsuarioBaja;
    }

    public void setTipoAcopioUsuarioBaja(String tipoAcopioUsuarioBaja) {
        this.tipoAcopioUsuarioBaja = tipoAcopioUsuarioBaja;
    }

    @OneToMany(mappedBy = "tipoAcopio")
    public Collection<IngresoAcopioEntity> getIngresoAcopio() {
        return ingresoAcopio;
    }

    public void setIngresoAcopio(Collection<IngresoAcopioEntity> ingresoAcopio) {
        this.ingresoAcopio = ingresoAcopio;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_medio_id", referencedColumnName = "ti_medio_id")
    public TipoMedioEntity getTipoMedio() {
        return tipoMedio;
    }

    public void setTipoMedio(TipoMedioEntity tipoMedio) {
        this.tipoMedio = tipoMedio;
    }

    //=================================================================


    @Override
    public String toString() {
        return tipoAcopioNombre;
    }
}
