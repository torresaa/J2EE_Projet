/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.OrdersFacade;
import entities.Orders;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
    private Orders orders;
    
    /**
     * Creates a new instance of OrdersView
     */
    public OrdersView() {
        this.orders = new Orders();
    }    
}
