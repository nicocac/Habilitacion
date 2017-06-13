package Datos;


import javax.persistence.*;

@Entity
@Table(name = "login_users")
public class LoginUsersEntity {


    private Integer loginId;
    private String loginNombre;
    private String loginDescripcion;


    //======================================================================================

    @Id
    @GeneratedValue
    @Column(name = "login_id")
    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    @Basic
    @Column(name = "login_nombre")
    public String getLoginNombre() {
        return loginNombre;
    }

    public void setLoginNombre(String loginNombre) {
        this.loginNombre = loginNombre;
    }

    @Basic
    @Column(name = "login_descripcion")
    public String getLoginDescripcion() {
        return loginDescripcion;
    }

    public void setLoginDescripcion(String loginDescripcion) {
        this.loginDescripcion = loginDescripcion;
    }

    //=================================================================

    @Override
    public String toString() {
        return getLoginNombre();
    }




}
