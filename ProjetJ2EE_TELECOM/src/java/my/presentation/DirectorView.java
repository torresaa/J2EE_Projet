/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.DirectorFacade;
import entities.Director;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author User
 */
@ManagedBean(name = "DirectorView")
@RequestScoped
public class DirectorView {
    @EJB
    private DirectorFacade directorFacade;
    private Director director;
    
    /**
     * Creates a new instance of DirectorView
     */
    public DirectorView() {
        this.director = new Director();
    }

    public Director getDirector() {
        return director;
    }
    
    public String postDirector(){   
        if(director.getDirectorName().equals("")){
            return "notvalidpost";
        }else{
            directorFacade.create(director);
            return "posteddirector";
        }
    }
    
    public int numberOfDirectors(){
        return directorFacade.findAll().size();
    }
}
