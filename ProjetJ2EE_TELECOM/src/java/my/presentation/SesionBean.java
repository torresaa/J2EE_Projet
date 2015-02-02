/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import entities.Users;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author aquilest
 */
@ManagedBean(name = "sesionBean")
@SessionScoped
public class SesionBean implements Serializable{
    private String header;
    private String toFind = "";
    
    private Users user;
    private boolean login = false;
    private boolean admin = false; 
    

    public String getToFind() {
        return toFind;
    }

    public void setToFind(String toFind) {
        this.toFind = toFind;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    
    public String goToIndex(){
        return "index";
    }
    
    public String goToSignin(){
        return "signup";
    }
    
    public String goToLogin(){
        return "login";
    }
}
