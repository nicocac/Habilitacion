package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by OWNER on 7/3/2016.
 */
@Entity
@Table(name = "egreso_acopio")
public class EgresoAcopioEntity {


    private Integer egresoId;
    private Date    egresoFecha;
    private Integer egresoCantidadTotal;
    private String motivo;

    private Date    egresoFechaAlta;
    private String  egresoUsuarioAlta;
    private Date    egresoFechaUltMod;
    private String  egresoUsuarioUltMod;
    private Date    egresoFechaBaja;
    private String  egresoUsuarioBaja;

    private String calle;
    private String nro;
    private String localidad;
    private String codPostal;
    private String provincia;
    private String chofer;


    private List<DetalleEgresoAcopioEntity> listaDetalleEgresoAcopioEntity;

//    private TipoAcopioEntity tipoAcopio;
//    private LoteEntity lote;
//    private CampaniaEntity campania;
    private ClienteEntity cliente;
//    private TipoGranoEntity tipoGrano;
    private TransporteEntity transporte;

    //======================================================================================

    @Id
    @GeneratedValue
    @Column(name = "egreso_id")

    public Integer getEgresoId() {
        return egresoId;
    }

    public void setEgresoId(Integer egresoId) {
        this.egresoId = egresoId;
    }
    @Column(name = "egreso_fecha")
    public Date getEgresoFecha() {
        return egresoFecha;
    }

    public void setEgresoFecha(Date egresoFecha) {
        this.egresoFecha = egresoFecha;
    }
    @Column(name = "egreso_cantidad_total")
    public Integer getEgresoCantidadTotal() {
        return egresoCantidadTotal;
    }

    public void setEgresoCantidadTotal(Integer egresoCantidadTotal) {
        this.egresoCantidadTotal = egresoCantidadTotal;
    }
    @Column(name = "egreso_motivo")
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    @Column(name = "egreso_fecha_alta")
    public Date getEgresoFechaAlta() {
        return egresoFechaAlta;
    }

    public void setEgresoFechaAlta(Date egresoFechaAlta) {
        this.egresoFechaAlta = egresoFechaAlta;
    }
    @Column(name = "egreso_usuario_alta")
    public String getEgresoUsuarioAlta() {
        return egresoUsuarioAlta;
    }

    public void setEgresoUsuarioAlta(String egresoUsuarioAlta) {
        this.egresoUsuarioAlta = egresoUsuarioAlta;
    }
    @Column(name = "egreso_fecha_ult_mod")
    public Date getEgresoFechaUltMod() {
        return egresoFechaUltMod;
    }

    public void setEgresoFechaUltMod(Date egresoFechaUltMod) {
        this.egresoFechaUltMod = egresoFechaUltMod;
    }
    @Column(name = "egreso_usuario_utl_mod")
    public String getEgresoUsuarioUltMod() {
        return egresoUsuarioUltMod;
    }

    public void setEgresoUsuarioUltMod(String egresoUsuarioUltMod) {
        this.egresoUsuarioUltMod = egresoUsuarioUltMod;
    }
    @Column(name = "egreso_fecha_baja")
    public Date getEgresoFechaBaja() {
        return egresoFechaBaja;
    }

    public void setEgresoFechaBaja(Date egresoFechaBaja) {
        this.egresoFechaBaja = egresoFechaBaja;
    }
    @Column(name = "egreso_usuario_baja")
    public String getEgresoUsuarioBaja() {
        return egresoUsuarioBaja;
    }

    public void setEgresoUsuarioBaja(String egresoUsuarioBaja) {
        this.egresoUsuarioBaja = egresoUsuarioBaja;
    }


    @OneToMany(mappedBy = "egresoAcopio")
    public List<DetalleEgresoAcopioEntity> getListaDetalleEgresoAcopioEntity() {
        return listaDetalleEgresoAcopioEntity;
    }

    public void setListaDetalleEgresoAcopioEntity(List<DetalleEgresoAcopioEntity> listaDetalleEgresoAcopioEntity) {
        this.listaDetalleEgresoAcopioEntity = listaDetalleEgresoAcopioEntity;
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
    @JoinColumn(name = "transporte_id", referencedColumnName = "transporte_id")
    public TransporteEntity getTransporte() {
        return transporte;
    }

    public void setTransporte(TransporteEntity transporte) {
        this.transporte = transporte;
    }

    @Column(name = "calle")
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @Column(name = "nro")
    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    @Column(name = "localidad")
    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Column(name = "cod_postal")
    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    @Column(name = "provincia")
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }


    @Column(name = "chofer")
    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }
}
