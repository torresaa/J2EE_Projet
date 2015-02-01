/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author aquilest
 */
@ManagedBean
@RequestScoped
public class Sesion {
    private String header;

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
