/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descriptors;

import entities.Dvd;

/**
 *
 * @author aquilest
 */
public class Product {
    private Dvd dvd;
    private boolean isShippable = false;

    public void setDvd(Dvd dvd) {
        this.dvd = dvd;
    }
    
    public Product(Dvd dvd){
        this.dvd = dvd;
    }
    
    public Dvd getDvd(){
        return this.dvd;
    }
    
    public void setSetShippable(boolean e){
        this.isShippable = e;
    }
    
    public boolean isShippable(){
        return this.isShippable;
    }
}
