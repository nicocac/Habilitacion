package Cliente;

import java.sql.Date;

/**
 * Created by jagm on 10/10/2016.
 */
public class Cliente {

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

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteDescripcion() {
        return clienteDescripcion;
    }

    public void setClienteDescripcion(String clienteDescripcion) {
        this.clienteDescripcion = clienteDescripcion;
    }

    public String getClienteCuitCuil() {
        return clienteCuitCuil;
    }

    public void setClienteCuitCuil(String clienteCuitCuil) {
        this.clienteCuitCuil = clienteCuitCuil;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteApellido() {
        return clienteApellido;
    }

    public void setClienteApellido(String clienteApellido) {
        this.clienteApellido = clienteApellido;
    }

    public Date getClienteFechaAlta() {
        return clienteFechaAlta;
    }

    public void setClienteFechaAlta(Date clienteFechaAlta) {
        this.clienteFechaAlta = clienteFechaAlta;
    }

    public String getClienteUsuarioAlta() {
        return clienteUsuarioAlta;
    }

    public void setClienteUsuarioAlta(String clienteUsuarioAlta) {
        this.clienteUsuarioAlta = clienteUsuarioAlta;
    }

    public Date getClienteFechaUltMod() {
        return clienteFechaUltMod;
    }

    public void setClienteFechaUltMod(Date clienteFechaUltMod) {
        this.clienteFechaUltMod = clienteFechaUltMod;
    }

    public String getClienteUsuarioUltMod() {
        return clienteUsuarioUltMod;
    }

    public void setClienteUsuarioUltMod(String clienteUsuarioUltMod) {
        this.clienteUsuarioUltMod = clienteUsuarioUltMod;
    }

    public Date getClienteFechaBaja() {
        return clienteFechaBaja;
    }

    public void setClienteFechaBaja(Date clienteFechaBaja) {
        this.clienteFechaBaja = clienteFechaBaja;
    }

    public String getClienteUsuarioBaja() {
        return clienteUsuarioBaja;
    }

    public void setClienteUsuarioBaja(String clienteUsuarioBaja) {
        this.clienteUsuarioBaja = clienteUsuarioBaja;
    }
}
