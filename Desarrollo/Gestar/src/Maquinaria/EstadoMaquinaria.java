package Maquinaria;

/**
 * Created by OWNER on 8/28/2016.
 */
public class EstadoMaquinaria {

    private String nombre;
    private String descripcion;

    public EstadoMaquinaria(String nombre, String descripcion) {
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
