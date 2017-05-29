package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by OWNER on 5/30/2016.
 */
//@Entity
//@Table(name = "stock_insumo", schema = "", catalog = "gestar")
public class StockInsumoEntity {

    private Integer sInsId;
    private Integer sInsTotal;

    private Date sInsFechaAlta;
    private String sInsUsuarioAlta;
    private Date sInsFechaUltMod;
    private String sInsUsuarioUtlMod;
    private Date sInsFechaBaja;
    private String sInsUsuarioBaja;

    private InsumoEntity insumoBySInsInsId;

    private Collection<MovimientosStockEntity> movimientosStocksBySInsId;

    //==================================================================================================
    private Integer sInsInsId;
    //==================================================================================================


    @Id
    @GeneratedValue
    @Column(name = "s_ins_id")
    public Integer getsInsId() {
        return sInsId;
    }

    public void setsInsId(Integer sInsId) {
        this.sInsId = sInsId;
    }

    @Column(name = "s_ins_total")
    public Integer getsInsTotal() {
        return sInsTotal;
    }

    public void setsInsTotal(Integer sInsTotal) {
        this.sInsTotal = sInsTotal;
    }



    @Column(name = "s_ins_fecha_alta")
    public Date getsInsFechaAlta() {
        return sInsFechaAlta;
    }

    public void setsInsFechaAlta(Date sInsFechaAlta) {
        this.sInsFechaAlta = sInsFechaAlta;
    }

    @Column(name = "s_ins_usuario_alta")
    public String getsInsUsuarioAlta() {
        return sInsUsuarioAlta;
    }

    public void setsInsUsuarioAlta(String sInsUsuarioAlta) {
        this.sInsUsuarioAlta = sInsUsuarioAlta;
    }

    @Column(name = "s_ins_fecha_ult_mod")
    public Date getsInsFechaUltMod() {
        return sInsFechaUltMod;
    }

    public void setsInsFechaUltMod(Date sInsFechaUltMod) {
        this.sInsFechaUltMod = sInsFechaUltMod;
    }

    @Column(name = "s_ins_usuario_ult_mod")
    public String getsInsUsuarioUtlMod() {
        return sInsUsuarioUtlMod;
    }

    public void setsInsUsuarioUtlMod(String sInsUsuarioUtlMod) {
        this.sInsUsuarioUtlMod = sInsUsuarioUtlMod;
    }

    @Column(name = "s_ins_fecha_baja")
    public Date getsInsFechaBaja() {
        return sInsFechaBaja;
    }

    public void setsInsFechaBaja(Date sInsFechaBaja) {
        this.sInsFechaBaja = sInsFechaBaja;
    }

    @Column(name = "s_ins_usuario_baja")
    public String getsInsUsuarioBaja() {
        return sInsUsuarioBaja;
    }

    public void setsInsUsuarioBaja(String sInsUsuarioBaja) {
        this.sInsUsuarioBaja = sInsUsuarioBaja;
    }

    @ManyToOne
    @JoinColumn(name = "s_ins_ins_id", referencedColumnName = "ins_id")
    public InsumoEntity getInsumoBySInsInsId() {
        return insumoBySInsInsId;
    }

    public void setInsumoBySInsInsId(InsumoEntity insumoBySInsInsId) {
        this.insumoBySInsInsId = insumoBySInsInsId;
    }

    @OneToMany(mappedBy = "stockInsumoByMosSInsId")
    public Collection<MovimientosStockEntity> getMovimientosStocksBySInsId() {
        return movimientosStocksBySInsId;
    }

    public void setMovimientosStocksBySInsId(Collection<MovimientosStockEntity> movimientosStocksBySInsId) {
        this.movimientosStocksBySInsId = movimientosStocksBySInsId;
    }

    //=================================================================

    //=================================================================

    public Integer getsInsInsId() {
        return sInsInsId;
    }

    public void setsInsInsId(Integer sInsInsId) {
        this.sInsInsId = sInsInsId;
    }
}
