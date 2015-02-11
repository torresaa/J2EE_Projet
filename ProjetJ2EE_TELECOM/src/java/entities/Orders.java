/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author User
 */
@Entity
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrder;
    private String status;
    private int quantity;
    @JoinColumn(name = "Dvd_idDVDs", referencedColumnName = "idDVDs")
    @ManyToOne(optional = false)
    private Dvd dvdidDVDs;
    @JoinColumn(name = "Users_idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)    
    private Users usersidUser;    

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Dvd getDvdidDVDs() {
        return dvdidDVDs;
    }

    public void setDvdidDVDs(Dvd dvdidDVDs) {
        this.dvdidDVDs = dvdidDVDs;
    }

    public Users getUsersidUser() {
        return usersidUser;
    }

    public void setUsersidUser(Users usersidUser) {
        this.usersidUser = usersidUser;
    }
         
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Orders[ id=" + idOrder + " ]";
    }
    
}
