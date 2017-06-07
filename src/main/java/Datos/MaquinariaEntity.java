package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;


@Entity
@Table(name = "maquinaria", schema = "", catalog = "gestar")
public class MaquinariaEntity {

    private Integer maqId;
    private String maqNombre;
    private String maqDescripcion;
    private String maqMarca;
    private String maqModelo;
    private String maqAnioFabricacion;

    private Date maqFechaAlta;
    private String maqUsuarioAlta;
    private Date maqFechaUltMod;
    private String maqUsuarioUtlMod;
    private Date maqFechaBaja;
    private String maqUsuarioBaja;
    private Long maqStock;
    private Long maqStockDisponible;

    private TipoEstadoMaquinariaEntity tipoEstadoMaquinariaByMaqTestadoId;
    private TipoMaquinariaEntity tipoMaquinariaByMaqTmaqId;

    private Collection<DetalleLaboreoEntity> detalleLaboreosByMaqId;

    //======================================================================================

    @Id
    @GeneratedValue
    @Column(name = "maq_id")
    public Integer getMaqId() {
        return maqId;
    }

    public void setMaqId(Integer maqId) {
        this.maqId = maqId;
    }

    @Basic
    @Column(name = "maq_nombre")
    public String getMaqNombre() {
        return maqNombre;
    }

    public void setMaqNombre(String maqNombre) {
        this.maqNombre = maqNombre;
    }

    @Basic
    @Column(name = "maq_descripcion")
    public String getMaqDescripcion() {
        return maqDescripcion;
    }

    public void setMaqDescripcion(String maqDescripcion) {
        this.maqDescripcion = maqDescripcion;
    }

    @Basic
    @Column(name = "maq_marca")
    public String getMaqMarca() {
        return maqMarca;
    }

    public void setMaqMarca(String maqMarca) {
        this.maqMarca = maqMarca;
    }

    @Basic
    @Column(name = "maq_modelo")
    public String getMaqModelo() {
        return maqModelo;
    }

    public void setMaqModelo(String maqModelo) {
        this.maqModelo = maqModelo;
    }

    @Basic
    @Column(name = "maq_anio_fabricacion")
    public String getMaqAnioFabricacion() {
        return maqAnioFabricacion;
    }

    public void setMaqAnioFabricacion(String maqAnioFabricacion) {
        this.maqAnioFabricacion = maqAnioFabricacion;
    }

    @Basic
    @Column(name = "maq_fecha_alta")
    public Date getMaqFechaAlta() {
        return maqFechaAlta;
    }

    public void setMaqFechaAlta(Date maqFechaAlta) {
        this.maqFechaAlta = maqFechaAlta;
    }

    @Basic
    @Column(name = "maq_usuario_alta")
    public String getMaqUsuarioAlta() {
        return maqUsuarioAlta;
    }

    public void setMaqUsuarioAlta(String maqUsuarioAlta) {
        this.maqUsuarioAlta = maqUsuarioAlta;
    }

    @Basic
    @Column(name = "maq_fecha_ult_mod")
    public Date getMaqFechaUltMod() {
        return maqFechaUltMod;
    }

    public void setMaqFechaUltMod(Date maqFechaUltMod) {
        this.maqFechaUltMod = maqFechaUltMod;
    }

    @Basic
    @Column(name = "maq_usuario_utl_mod")
    public String getMaqUsuarioUtlMod() {
        return maqUsuarioUtlMod;
    }
    public void setMaqUsuarioUtlMod(String maqUsuarioUtlMod) {
        this.maqUsuarioUtlMod = maqUsuarioUtlMod;
    }

    @Basic
    @Column(name = "maq_fecha_baja")
    public Date getMaqFechaBaja() {
        return maqFechaBaja;
    }

    public void setMaqFechaBaja(Date maqFechaBaja) {
        this.maqFechaBaja = maqFechaBaja;
    }

    @Basic
    @Column(name = "maq_usuario_baja")
    public String getMaqUsuarioBaja() {
        return maqUsuarioBaja;
    }

    public void setMaqUsuarioBaja(String maqUsuarioBaja) {
        this.maqUsuarioBaja = maqUsuarioBaja;
    }

    @Column(name = "maq_stock")
    public Long getMaqStock() {
        return maqStock;
    }

    public void setMaqStock(Long maqStock) {
        this.maqStock = maqStock;
    }

    @Column(name = "maq_stock_disponible")
    public Long getMaqStockDisponible() {
        return maqStockDisponible;
    }

    public void setMaqStockDisponible(Long maqStockDisponible) {
        this.maqStockDisponible = maqStockDisponible;
    }

    @ManyToOne
    @JoinColumn(name = "maq_testado_id", referencedColumnName = "te_ma_id")
    public TipoEstadoMaquinariaEntity getTipoEstadoMaquinariaByMaqTestadoId() {
        return tipoEstadoMaquinariaByMaqTestadoId;
    }

    public void setTipoEstadoMaquinariaByMaqTestadoId(TipoEstadoMaquinariaEntity tipoEstadoMaquinariaByMaqTestadoId) {
        this.tipoEstadoMaquinariaByMaqTestadoId = tipoEstadoMaquinariaByMaqTestadoId;
    }
//

    @ManyToOne
    @JoinColumn(name = "maq_tmaq_id", referencedColumnName = "tma_id")
    public TipoMaquinariaEntity getTipoMaquinariaByMaqTmaqId() {
        return tipoMaquinariaByMaqTmaqId;
    }

    public void setTipoMaquinariaByMaqTmaqId(TipoMaquinariaEntity tipoMaquinariaByMaqTmaqId) {
        this.tipoMaquinariaByMaqTmaqId = tipoMaquinariaByMaqTmaqId;
    }



    @OneToMany(mappedBy = "maquinariaByDboMaqId")
    public Collection<DetalleLaboreoEntity> getDetalleLaboreosByMaqId() {
        return detalleLaboreosByMaqId;
    }

    public void setDetalleLaboreosByMaqId(Collection<DetalleLaboreoEntity> detalleLaboreosByMaqId) {
        this.detalleLaboreosByMaqId = detalleLaboreosByMaqId;
    }


    //=================================================================
    @Override
    public String toString() {
        return getMaqNombre();
    }
    //=================================================================


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaquinariaEntity that = (MaquinariaEntity) o;

        if (maqId != that.maqId) return false;
        if (maqNombre != null ? !maqNombre.equals(that.maqNombre) : that.maqNombre != null) return false;
        if (maqDescripcion != null ? !maqDescripcion.equals(that.maqDescripcion) : that.maqDescripcion != null)
            return false;
        if (maqMarca != null ? !maqMarca.equals(that.maqMarca) : that.maqMarca != null) return false;
        if (maqModelo != null ? !maqModelo.equals(that.maqModelo) : that.maqModelo != null) return false;
        if (maqAnioFabricacion != null ? !maqAnioFabricacion.equals(that.maqAnioFabricacion) : that.maqAnioFabricacion != null)
            return false;
        if (maqFechaAlta != null ? !maqFechaAlta.equals(that.maqFechaAlta) : that.maqFechaAlta != null) return false;
        if (maqUsuarioAlta != null ? !maqUsuarioAlta.equals(that.maqUsuarioAlta) : that.maqUsuarioAlta != null)
            return false;
        if (maqFechaUltMod != null ? !maqFechaUltMod.equals(that.maqFechaUltMod) : that.maqFechaUltMod != null)
            return false;
        if (maqUsuarioUtlMod != null ? !maqUsuarioUtlMod.equals(that.maqUsuarioUtlMod) : that.maqUsuarioUtlMod != null)
            return false;
        if (maqFechaBaja != null ? !maqFechaBaja.equals(that.maqFechaBaja) : that.maqFechaBaja != null) return false;
        if (maqUsuarioBaja != null ? !maqUsuarioBaja.equals(that.maqUsuarioBaja) : that.maqUsuarioBaja != null)
            return false;

        return true;
    }





}
