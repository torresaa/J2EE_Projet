/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.DvdFacade;
import entities.Dvd;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author User
 */
@ManagedBean(name = "DvdView")
@RequestScoped
public class DvdView {
    @EJB
    private DvdFacade dvdFacade;
    private Dvd dvd;
    private String toFind = "";    
    private DirectorView directorview = new DirectorView();
    private AuthorView authorview = new AuthorView();
    
    public String getToFind() {
        return toFind;
    }

    public void setToFind(String toFind) {
        this.toFind = toFind;
    }
    
    
    /**
     * Creates a new instance of DvdView
     */
    public DvdView() {
        this.dvd =  new Dvd();
    }
    
    public Dvd getDvd(){
        return dvd;
    }
    
    public String postDvd(){
        if(dvdFacade.find(dvd.getTitle())!=null || dvd.getTitle().equals("")){
            return "notvalidpost";
        }else{
            dvdFacade.create(dvd);
            return "posted";
        }
    }
    
    public int numberOfDvds(){
        return dvdFacade.findAll().size();
    }
    
    public String findDvd(){
        if (dvdFacade.find(this.toFind)!= null){            
            return "found";
        }else{
            directorview.setToFind(this.toFind);            
            return "nothing";
        }        
    }
    
    
}
