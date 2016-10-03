package Laboreo;

/**
 * Created by OWNER on 9/4/2016.
 */
public class MomentoLaboreo {
    private String nombre;
    private String descripcion;

    public MomentoLaboreo(String nombre, String descripcion) {
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

    public String toString(){
        return this.nombre +" - "+this.descripcion;
    }
}
