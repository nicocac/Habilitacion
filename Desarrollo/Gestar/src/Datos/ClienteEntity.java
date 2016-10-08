package Datos;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by OWNER on 8/9/2016.
 */
@Entity
@Table(name = "cliente", schema = "", catalog = "gestar")
public class ClienteEntity {

    private Integer clienteId;
    private String clienteDescripcion;
    private String clienteCuitCuil;
    private String clienteNombre;
    private String clienteApellido;

    private Date clienteFechaAlta;
    private String clienteUsuarioAlta;
    private Date clienteFechaUltMod;
    private String clienteUsuarioUltMod;
    private Date clienteFechaBaja;
    private String clienteUsuarioBaja;

    //=================================================================

    @Id
    @GeneratedValue
    @Column(name = "cliente_id")
    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    @Column(name = "cliente_descripcion")
    public String getClienteDescripcion() {
        return clienteDescripcion;
    }

    public void setClienteDescripcion(String clienteDescripcion) {
        this.clienteDescripcion = clienteDescripcion;
    }

    @Column(name = "cliente_nombre")
    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    @Column(name = "cliente_apellido")
    public String getClienteApellido() {
        return clienteApellido;
    }

    public void setClienteApellido(String clienteApellido) {
        this.clienteApellido = clienteApellido;
    }

    @Column(name = "cliente_cuit_cuil")
    public String getClienteCuitCuil() {
        return clienteCuitCuil;
    }

    public void setClienteCuitCuil(String clienteCuitCuil) {
        this.clienteCuitCuil = clienteCuitCuil;
    }

    @Column(name = "cliente_fecha_alta")
    public Date getClienteFechaAlta() {
        return clienteFechaAlta;
    }

    public void setClienteFechaAlta(Date clienteFechaAlta) {
        this.clienteFechaAlta = clienteFechaAlta;
    }

    @Column(name = "cliente_usuario_alta")
    public String getClienteUsuarioAlta() {
        return clienteUsuarioAlta;
    }

    public void setClienteUsuarioAlta(String clienteUsuarioAlta) {
        this.clienteUsuarioAlta = clienteUsuarioAlta;
    }

    @Column(name = "cliente_fecha_ult_mod")
    public Date getClienteFechaUltMod() {
        return clienteFechaUltMod;
    }

    public void setClienteFechaUltMod(Date clienteFechaUltMod) {
        this.clienteFechaUltMod = clienteFechaUltMod;
    }

    @Column(name = "cliente_usuario_ult_mod")
    public String getClienteUsuarioUltMod() {
        return clienteUsuarioUltMod;
    }

    public void setClienteUsuarioUltMod(String clienteUsuarioUltMod) {
        this.clienteUsuarioUltMod = clienteUsuarioUltMod;
    }

    @Column(name = "cliente_fecha_baja")
    public Date getClienteFechaBaja() {
        return clienteFechaBaja;
    }

    public void setClienteFechaBaja(Date clienteFechaBaja) {
        this.clienteFechaBaja = clienteFechaBaja;
    }

    @Column(name = "cliente_usuario_baja")
    public String getClienteUsuarioBaja() {
        return clienteUsuarioBaja;
    }

    public void setClienteUsuarioBaja(String clienteUsuarioBaja) {
        this.clienteUsuarioBaja = clienteUsuarioBaja;
    }

    //=================================================================


    @Override
    public String toString() {
        return "ClienteEntity{" +
                "clienteDescripcion='" + clienteDescripcion + '\'' +
                '}';
    }
}

