/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.DvdFacade;
import boundary.OrdersFacade;
import descriptors.Product;
import entities.Dvd;
import entities.Orders;
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
    private OrdersFacade ordersFacade;
    @EJB
    private DvdFacade dvdFacade;
    private String header;
    private String toFind = "";

    private Users user;
    private boolean logged;
    private boolean admin;
    private boolean chartVerified;
    private List<Product> chartList = new ArrayList<>();
    private List<Dvd> dvdList = new ArrayList<>();
    private List<Orders> ordersList = new ArrayList<>();
    private int creditCardNumber = 0;
    private String creditCardExpirationDate = "";
    private int creditCardCode = 0;

    private String status = null;

    public SesionBean() {
        this.user = new Users();
        this.logged = false;
        this.admin = false;
        this.chartVerified = false;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<Product> getChartList() {
        return chartList;
    }

    public void setChartList(List<Product> chartList) {
        this.chartList = chartList;
    }

    public String insertOrder(Dvd dvd) {
        Product product = new Product(dvd);
        this.chartList.add(product);
        return "found";
    }

    public void removeOder(Product dvd) {
        this.chartList.remove(dvd);
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

    public String logOut() {
        this.user = null;
        this.logged = false;
        this.admin = false;
        this.chartVerified = false;
        this.chartList = null;
        this.dvdList = null;
        this.ordersList = null;
        return "index";
    }

    public String displayStock() {
        this.dvdList = dvdFacade.findAll();
        return "stock_list";
        //HAY QUE HACER PAG PA LISTA DEL ADMIN 
    }

    public String updateDvd(Dvd dvd) {
        dvdFacade.updateDvdAdd(dvd);
        return "stock_list";
    }

    public String chartOption() {

        if (this.logged) {
            if (!this.chartList.isEmpty() && this.chartVerified) {
                for (int i = 0; i < this.chartList.size(); i++) {
                    Dvd dvd;
                    dvd = dvdFacade.find(this.chartList.get(i).getDvd().getIdDVDs());
                    if (dvd.getQuantity() < this.chartList.get(i).getDvd().getQuantity()) {
                        this.chartList.get(i).setSetShippable(false);
                    } else {
                        this.chartList.get(i).setSetShippable(true);
                    }
                }
                this.chartVerified = true;
                return "mychart_verify";
            } else {
                return "mychart";
            }
        } else {
            return "mychart";
        }

    }

    public void setIndexAdmin() {
        this.ordersList = null;
        this.ordersList = ordersFacade.getOrdersEnAttente();
    }
    
    public boolean isStatusNull(){
        return this.status == null;
        //return this.status.equals("");
    }
    
    public boolean isStatusOk(){
        return this.status.equals("ok");
    }
    
}
