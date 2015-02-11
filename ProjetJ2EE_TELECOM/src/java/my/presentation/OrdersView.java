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
    private SesionBean sesion;
    
    /**
     * Creates a new instance of OrdersView
     */
    public OrdersView() {
        this.orders = new Orders();
    }    
    
    public SesionBean getSesion() {
        return sesion;
    }

    public void setSesion(SesionBean sesion) {
        this.sesion = sesion;
    }    
    
    public String payGestion(){
        if (sesion.isLogged()){
            for(int i = 0; i < sesion.getChartList().size(); i++){
                Dvd dvd;
                dvd = dvdFacade.find(sesion.getChartList().get(i).getDvd().getIdDVDs());
                if (dvd.getQuantity() < sesion.getChartList().get(i).getDvd().getQuantity()){
                    sesion.getChartList().get(i).setSetShippable(false);
                }else{
                    sesion.getChartList().get(i).setSetShippable(true);
                }
            }
            sesion.setChartVerified(true);
            return "mychart_verify";
        }else{
            return "login";
        }
    }
    
    public String postOrders(){
        if (sesion.getCreditCardCode() == 0 || sesion.getCreditCardNumber() == 0 
                || sesion.getCreditCardExpirationDate().equals("")){
            return "paypage_wrong";
        }else{
            for (int i = 0; i < sesion.getChartList().size(); i++){
                Orders order = new Orders();
                order.setDvdidDVDs(sesion.getChartList().get(i).getDvd());
                order.setUsersidUser(sesion.getUser());
                if(sesion.getChartList().get(i).isShippable()){
                    order.setStatus("En cours");
                    dvdFacade.updateDvdSubstract(sesion.getChartList().get(i).getDvd());
                }else{
                    order.setStatus("En attente");
                } 
                order.setQuantity(sesion.getChartList().get(i).getDvd().getQuantity());
                ordersFacade.create(order);
            }
            return "index_afterpay";
        }        
    }
        
    
    public String getDispatchedOrders(){
        this.sesion.setOrdersList(this.ordersFacade.getDispatchedOrders());        
        return "dispatched_orders";
    }
    
    
}