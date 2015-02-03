/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.DvdFacade;
import boundary.OrdersFacade;
import entities.Dvd;
import entities.Orders;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author User
 */
@ManagedBean(name = "ordersView")
@RequestScoped
public class OrdersView {

    private static final long serialVersionUID = 1L;
    @EJB
    private OrdersFacade ordersFacade;
    @EJB
    private DvdFacade dvdFacade;    
    private Orders orders;
    @ManagedProperty(value = "#{sesionBean}")
    private SesionBean sesionBean;
    
    /**
     * Creates a new instance of OrdersView
     */
    public OrdersView() {
        this.orders = new Orders();
    }    
    
    public SesionBean getSesionBean() {
        return sesionBean;
    }

    public void setSesionBean(SesionBean sesionBean) {
        this.sesionBean = sesionBean;
    }    
    
    public String payGestion(){
        if (sesionBean.isLogged()){
            for(int i = 0; i < sesionBean.getOrdersList().size(); i++){
                Dvd dvd;
                dvd = dvdFacade.find(sesionBean.getOrdersList().get(i).getDvd().getIdDVDs());
                if (dvd.getQuantity() < sesionBean.getOrdersList().get(i).getDvd().getQuantity()){
                    sesionBean.getOrdersList().get(i).setSetShippable(false);
                }else{
                    sesionBean.getOrdersList().get(i).setSetShippable(true);
                }
            }
            return "mychart_verify";
        }else{
            return "login";
        }
    }
    
    public String postOrders(){
        for (int i = 0; i < sesionBean.getOrdersList().size(); i++){
            Orders order = new Orders();
            order.setDvdidDVDs(sesionBean.getOrdersList().get(i).getDvd());
            order.setUseridUser(sesionBean.getUser());
            if(sesionBean.getOrdersList().get(i).isShippable()){
                order.setStatus("En cours");
            }else{
                order.setStatus("En attente");
            }              
            ordersFacade.create(order);
        }
        return "TODO";
    }
    
}