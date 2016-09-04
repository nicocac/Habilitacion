package Datos;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by OWNER on 9/4/2016.
 */
public class LaboreoLoteCampaniaEntityPK implements Serializable {
    private int llcLcpId;
    private int llcLboId;

    @Column(name = "llc_lcp_id")
    @Id
    public int getLlcLcpId() {
        return llcLcpId;
    }

    public void setLlcLcpId(int llcLcpId) {
        this.llcLcpId = llcLcpId;
    }

    @Column(name = "llc_lbo_id")
    @Id
    public int getLlcLboId() {
        return llcLboId;
    }

    public void setLlcLboId(int llcLboId) {
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
        int result = llcLcpId;
        result = 31 * result + llcLboId;
        return result;
    }
}
