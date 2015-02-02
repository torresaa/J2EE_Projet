/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AuthorFacade;
import boundary.DirectorFacade;
import boundary.DvdFacade;
import entities.Author;
import entities.Director;
import entities.Dvd;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author User
 */
@ManagedBean(name = "dvdView")
@RequestScoped
public class DvdView {
    @EJB
    private DirectorFacade directorFacade;
    @EJB
    private AuthorFacade authorFacade;
    @EJB
    private DvdFacade dvdFacade;        
    private List<Dvd> dvdList= new ArrayList<>();
    private Dvd dvd;
    private Author author;
    private Director director;
    private String toFind = "";        
    private String authorName = "";
    private String directorName = "";

    public List<Dvd> getDvdList() {
        return dvdList;
    }

    public void setDvdList(List<Dvd> dvdList) {
        this.dvdList = dvdList;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
    
    
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
        this.author = new Author();
        this.director = new Director();
    }
    
    public Dvd getDvd(){
        return dvd;
    }
    
    public String postDvd(){
        if(dvd.getQuantity() == 0 || dvd.getDvdTitle().equals("")
                || this.directorName.equals("") 
                || this.authorName.equals("")){
            return "notvalidpost";
        }else{      
            if (authorFacade.findByName(authorName) == null){
                author.setAuthorName(authorName);
                authorFacade.create(author);
            }
            if (directorFacade.findByName(directorName) == null){
                director.setDirectorName(directorName);
                directorFacade.create(director);
            }
            dvd.setAuthoridAuthor(authorFacade.findByName(authorName));
            dvd.setDirectoridDirector(directorFacade.findByName(directorName));
            dvdFacade.create(dvd);
            return "posted";
        }
    }
    
    public int numberOfDvds(){
        return dvdFacade.findAll().size();
    }
    
    public String findDvd(){
        this.dvdList= dvdFacade.findByName(toFind);
        if (this.dvdList.isEmpty()){            
            this.dvdList = dvdFacade.findDvdByAuthor(toFind);
            if (this.dvdList.isEmpty()){
                this.dvdList = dvdFacade.findDvdByDirector(toFind);
                if (this.dvdList.isEmpty()){
                    return "nothing";
                }else{
                    return "found";
                }
            }else{
                return "found";
            }            
        }else{            
            return "found";            
        }        
    }
    
    public String findByAuthor(){
        if (dvdFacade.findDvdByAuthor(toFind) == null){
            return "nothing";
        }else{
            this.author.setDvdList(dvdFacade.findDvdByAuthor(toFind));
            this.dvdList = this.author.getDvdList();
            return "found";
        }
        
    }
    
    public String findByDirector(){
        this.director.setDvdList(dvdFacade.findDvdByDirector(toFind));
        if (dvdFacade.findDvdByDirector(toFind)==null){
            return "nothing";
        }else{
            this.dvdList= this.director.getDvdList();
            return "found";
        }
    }
}
