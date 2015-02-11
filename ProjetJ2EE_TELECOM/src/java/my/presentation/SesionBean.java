/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.DvdFacade;
import descriptors.Product;
import entities.Dvd;
import entities.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author aquilest
 */
@ManagedBean(name = "sesionBean")
@SessionScoped
public class SesionBean implements Serializable {
    
    @EJB
    private DvdFacade dvdFacade;    
    private String header;
    private String toFind = "";

    private Users user;
    private boolean logged;
    private boolean admin;
    private boolean chartVerified;
    private List<Product> ordersList = new ArrayList<>();
    private List<Dvd> dvdList = new ArrayList<>();
    private int creditCardNumber = 0;
    private String creditCardExpirationDate = "";
    private int creditCardCode = 0;

    public SesionBean() {
        this.user = new Users();
        this.logged = false;
        this.admin = false;
        this.chartVerified = false;
    }    

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardExpirationDate() {
        return creditCardExpirationDate;
    }

    public void setCreditCardExpirationDate(String creditCardExpirationDate) {
        this.creditCardExpirationDate = creditCardExpirationDate;
    }

    public int getCreditCardCode() {
        return creditCardCode;
    }

    public void setCreditCardCode(int creditCardCode) {
        this.creditCardCode = creditCardCode;
    }

    public boolean isChartVerified() {
        return chartVerified;
    }

    public void setChartVerified(boolean chartVerified) {
        this.chartVerified = chartVerified;
    }
        
    public List<Dvd> getDvdList() {
        return dvdList;
    }

    public void setDvdList(List<Dvd> dvdList) {
        this.dvdList = dvdList;
    }
            
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean loged) {
        this.logged = loged;
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

    public String insertOrder(Dvd dvd) {       
        Product product = new Product(dvd);
        this.ordersList.add(product);
        return "found";
    }

    public void removeOder(Product dvd) {        
        this.ordersList.remove(dvd);
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
        this.logged = false;
        this.admin = false;
        this.chartVerified = false;
        return "index";
    }
    
    public String chartOption(){
        if (this.logged){
            if (!this.ordersList.isEmpty() && this.chartVerified){
                for(int i = 0; i < this.ordersList.size(); i++){
                    Dvd dvd;
                    dvd = dvdFacade.find(this.ordersList.get(i).getDvd().getIdDVDs());
                    if (dvd.getQuantity() < this.ordersList.get(i).getDvd().getQuantity()){
                        this.ordersList.get(i).setSetShippable(false);
                    }else{
                        this.ordersList.get(i).setSetShippable(true);
                    }
                }
                this.chartVerified = true;
                return "mychart_verify";
            }else{
                return "mychart";
            }
        }else{
            return "mychart";
        }
    }
                    
}
