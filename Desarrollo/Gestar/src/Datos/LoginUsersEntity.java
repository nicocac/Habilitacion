package Datos;


import javax.persistence.*;

@Entity
@Table(name = "login_users", schema = "gestar")
public class LoginUsersEntity {

    @Id
    @GeneratedValue
    @Column(name = "tpo_id")
    private int loginId;

    @Column(name = "login_nombre")
    private String loginNombre;

    @Column(name = "login_descripcion")
    private String loginDescripcion;

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getLoginNombre() {
        return loginNombre;
    }

    public void setLoginNombre(String loginNombre) {
        this.loginNombre = loginNombre;
    }

    public String getLoginDescripcion() {
        return loginDescripcion;
    }

    public void setLoginDescripcion(String loginDescripcion) {
        this.loginDescripcion = loginDescripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginUsersEntity that = (LoginUsersEntity) o;

        if (loginId != that.loginId) return false;
        if (loginNombre != null ? !loginNombre.equals(that.loginNombre) : that.loginNombre != null) return false;
        if (loginDescripcion != null ? !loginDescripcion.equals(that.loginDescripcion) : that.loginDescripcion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = loginId;
        result = 31 * result + (loginNombre != null ? loginNombre.hashCode() : 0);
        result = 31 * result + (loginDescripcion != null ? loginDescripcion.hashCode() : 0);
        return result;
    }
}
