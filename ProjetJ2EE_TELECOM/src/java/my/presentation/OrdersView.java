/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.OrdersFacade;
import entities.Dvd;
import entities.Orders;
import entities.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author User
 */
@ManagedBean(name = "ordersView")
@SessionScoped
public class OrdersView implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private OrdersFacade ordersFacade;
    private List<Dvd> ordersList;
    private Orders orders;
    private int quantity;
    private Dvd dvd;
    private boolean loged = false;
    private Users user;
    private boolean fromOrders = false;
    
    /**
     * Creates a new instance of OrdersView
     */
    public OrdersView() {
        user = new Users();
        this.orders = new Orders();
        this.ordersList = new ArrayList<>();
    }

    public boolean isFromOrders() {
        return fromOrders;
    }

    public void setFromOrders(boolean fromOrders) {
        this.fromOrders = fromOrders;
    }

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
    
    public Dvd getDvd() {
        return this.dvd;
    }

    public void setDvd(Dvd dvd) {
        this.dvd = dvd;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Dvd> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Dvd> ordersList) {
        this.ordersList = ordersList;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String insertOrder(Dvd dvd) {
        this.ordersList.add(dvd);  
        if (!loged){
            fromOrders = true;
            return "login";
        }
        return "";
    }

    public String editAction() {

        Map<String, String> params;
        params = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String action = params.get("action");
        //...
        System.out.println("Action = " + action);
        return action;
    }

    public void removeOder(Dvd dvd) {
        this.ordersList.remove(dvd);
    }

    public String seeMyChart() {
        if (this.ordersList.isEmpty()) {
            return "";
        } else {
            if (loged){
                return "mychart";
            }else{
                fromOrders = true;
                return "login";    
            }
        }
    }
}
