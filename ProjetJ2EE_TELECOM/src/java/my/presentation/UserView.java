/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.UsersFacade;
import entities.Users;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author User
 */
@ManagedBean(name = "userView")
@RequestScoped
public class UserView {

    @EJB
    private UsersFacade usersFacade;
    private Users user;
    @ManagedProperty(value = "#{sesionBean}")
    private SesionBean sesion;

    /**
     * Creates a new instance of UserView
     */
    public UserView() {
        this.user = new Users();
    }

    public SesionBean getSesion() {
        return sesion;
    }

    public void setSesion(SesionBean sesion) {
        this.sesion = sesion;
    }

    public Users getUser() {
        return user;
    }

    public String postUser() {
        if (this.user.getEmail() != null && this.user.getName() != null
                && this.user.getPassword() != null
                && this.user.getUsername() != null) {
            usersFacade.create(user);
            return "validsignup";
        } else {
            return "signup_wrong";
        }
    }

    public String login() {
        if (this.user.getUsername() != null && this.user.getPassword() != null) {
            Users verifyUser;
            verifyUser = this.usersFacade.findUser(user.getUsername(), user.getPassword());
            if (verifyUser == null) {
                verifyUser = this.usersFacade.findUserByEmail(user.getUsername(), user.getPassword());
                if (verifyUser == null) {
                    return "login_wrong";
                } else {
                    if (verifyUser.getUsername().equals("Jorge") || verifyUser.getUsername().equals("Aquiles")
                            || verifyUser.getUsername().equals("Omar")) {
                        sesion.setLogged(true);
                        sesion.setUser(verifyUser);
                        sesion.setAdmin(true);
                    } else {
                        sesion.setLogged(true);
                        sesion.setUser(verifyUser);
                    }
                }
            } else {
                if (user.getUsername().equals("Jorge") || user.getUsername().equals("Aquiles")
                        || user.getUsername().equals("Omar")) {
                    sesion.setLogged(true);
                    sesion.setUser(verifyUser);
                    sesion.setAdmin(true);
                } else {
                    sesion.setLogged(true);
                    sesion.setUser(verifyUser);
                }
            }
        } else {
            return "login_wrong";
        }
        if (sesion.isAdmin()){
            this.sesion.setIndexAdmin();
        }
        return "index";
    }
}
