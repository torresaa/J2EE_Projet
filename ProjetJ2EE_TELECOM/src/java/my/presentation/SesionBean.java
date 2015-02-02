/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import descriptors.Product;
import entities.Dvd;
import entities.Users;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author aquilest
 */
@ManagedBean(name = "sesionBean")
@SessionScoped
public class SesionBean implements Serializable {

    private String header;
    private String toFind = "";

    private Users user;
    private boolean loged = false;
    private boolean admin = false;
    private List<Product> ordersList;
    
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public boolean isLoged() {
        return loged;
    }

    public void setLoged(boolean loged) {
        this.loged = loged;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Product> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Product> ordersList) {
        this.ordersList = ordersList;
    }

    public void insertOrder(Dvd dvd) {
        this.ordersList.add(new Product(dvd));
    }

    public void removeOder(Product p) {
        this.ordersList.remove(p);
    }
    
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
    
    public String logOut(){
        this.user = null;
        this.loged = false;
        this.admin = false;
        return "index";
    }
}
