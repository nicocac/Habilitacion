package Datos;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by OWNER on 9/4/2016.
 */
//TODO BORRAR
public class LaboreoLoteCampaniaEntityPK implements Serializable {
    private Integer llcLcpId;
    private Integer llcLboId;

    @Column(name = "llc_lcp_id")
    @Id
    public Integer getLlcLcpId() {
        return llcLcpId;
    }

    public void setLlcLcpId(Integer llcLcpId) {
        this.llcLcpId = llcLcpId;
    }

    @Column(name = "llc_lbo_id")
    @Id
    public Integer getLlcLboId() {
        return llcLboId;
    }

    public void setLlcLboId(Integer llcLboId) {
        this.llcLboId = llcLboId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LaboreoLoteCampaniaEntityPK that = (LaboreoLoteCampaniaEntityPK) o;

        if (llcLcpId != that.llcLcpId) return false;
        if (llcLboId != that.llcLboId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Integer result = llcLcpId;
        result = 31 * result + llcLboId;
        return result;
    }
}
