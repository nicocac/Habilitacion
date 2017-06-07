package Maquinaria;

/**
 * Created by OWNER on 8/28/2016.
 */
public class Maquinaria {
    private String nombre;
    private String descripcion;
    private String marca;
    private String modelo;
    private String modeloAnio;
    private TipoMaquinaria tipo;
    private EstadoMaquinaria estado;

    private Long stock;
    private Long stockDisponible;

    public Maquinaria() {
    }

    public Maquinaria(String nombre, String descripcion, String marca, String modelo, String modeloAnio, TipoMaquinaria tipo, EstadoMaquinaria estado) {
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setMarca(marca);
        this.setModelo(modelo);
        this.setModeloAnio(modeloAnio);
        this.setTipo(tipo);
        this.setEstado(estado);
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getModeloAnio() {
        return modeloAnio;
    }

    public void setModeloAnio(String modeloAnio) {
        this.modeloAnio = modeloAnio;
    }

    public TipoMaquinaria getTipo() {
        return tipo;
    }

    public void setTipo(TipoMaquinaria tipo) {
        this.tipo = tipo;
    }

    public EstadoMaquinaria getEstado() {
        return estado;
    }

    public void setEstado(EstadoMaquinaria estado) {
        this.estado = estado;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(Long stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public String toString (){
        return this.nombre+", "+this.getMarca()+", "+ this.modelo+", "+this.modeloAnio;
    }
}
