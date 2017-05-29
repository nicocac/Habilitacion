package Maquinaria;

/**
 * Created by OWNER on 8/28/2016.
 */
public class TipoMaquinaria {

    private String tmaNombre;
    private String tmaDescripcion;

    public TipoMaquinaria(String tmaNombre, String tmaDescripcion) {
        this.tmaNombre = tmaNombre;
        this.tmaDescripcion = tmaDescripcion;
    }

    public String getTmaNombre() {
        return tmaNombre;
    }

    public void setTmaNombre(String tmaNombre) {
        this.tmaNombre = tmaNombre;
    }

    public String getTmaDescripcion() {
        return tmaDescripcion;
    }

    public void setTmaDescripcion(String tmaDescripcion) {
        this.tmaDescripcion = tmaDescripcion;
    }
}
