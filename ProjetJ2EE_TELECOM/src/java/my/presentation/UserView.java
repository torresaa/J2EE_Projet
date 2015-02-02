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
    @ManagedProperty(value = "#{ordersView}")
    private OrdersView ordersView;
    /**
     * Creates a new instance of UserView
     */
    public UserView() {
        this.user = new Users();              
    }

    public OrdersView getOrdersView() {
        return ordersView;
    }

    public void setOrdersView(OrdersView ordersView) {
        this.ordersView = ordersView;
    }

    public Users getUser() {
        return user;
    }
    
    public String postUser(){
        if (this.user.getEmail()!=null && this.user.getName()!=null
                && this.user.getPassword()!=null 
                && this.user.getUsername()!=null){
            usersFacade.create(user);
            return "validsignup";
        }else{
            return "";
        }        
    }
    
    public String login(){
        if(this.user.getUsername()!=null && this.user.getPassword()!= null){
            this.user = this.usersFacade.findUser(user.getUsername(), user.getPassword());
            if (this.user == null){
                this.user = this.usersFacade.findUserByEmail(user.getUsername(), user.getPassword());
                if (this.user == null){
                    return "";
                }else{
                    ordersView.setLoged(true);
                    ordersView.setUser(user);
                    if (!ordersView.isFromOrders()){
                        if (user.getUsername().equals("Jorge")||user.getUsername().equals("Aquiles")
                                ||user.getUsername().equals("Omar")){
                            return "admin";
                        }else{
                            return "client";
                        }
                    }else{
                        return "mychart";
                    }
                }
            }else{
                if (user.getUsername().equals("Jorge")||user.getUsername().equals("Aquiles")
                            ||user.getUsername().equals("Omar")){
                    return "admin";
                }else{
                    return "client";
                }                
            }
        }else{
            return "";
        }
    }
}
