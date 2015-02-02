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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author User
 */
@ManagedBean(name = "DvdView")
@SessionScoped
public class DvdView {

    @EJB
    private DirectorFacade directorFacade;
    @EJB
    private AuthorFacade authorFacade;
    @EJB
    private DvdFacade dvdFacade;

    private List<Dvd> dvdList = new ArrayList<>();
    private Dvd dvd;
    private Author author;
    private Director director;
    private String authorName = "";
    private String directorName = "";

    @ManagedProperty(value = "#{SesionBean}")
    private SesionBean sesion;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public SesionBean getSesion() {
        return sesion;
    }

    public void setSesion(SesionBean sesion) {
        this.sesion = sesion;
    }

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

    /**
     * Creates a new instance of DvdView
     */
    public DvdView() {
        this.dvd = new Dvd();
        this.author = new Author();
        this.director = new Director();
    }

    public Dvd getDvd() {
        return dvd;
    }

    public void setDvd(Dvd dvd) {
        this.dvd = dvd;
    }

    public String postDvd() {
        if (dvd.getQuantity() == 0 || dvd.getDvdTitle().equals("")
                || this.directorName.equals("")
                || this.authorName.equals("")) {
            return "notvalidpost";
        } else {
            if (authorFacade.findByName(authorName) == null) {
                author.setAuthorName(authorName);
                authorFacade.create(author);
            }
            if (directorFacade.findByName(directorName) == null) {
                director.setDirectorName(directorName);
                directorFacade.create(director);
            }
            dvd.setAuthoridAuthor(authorFacade.findByName(authorName));
            dvd.setDirectoridDirector(directorFacade.findByName(directorName));
            dvdFacade.create(dvd);
            return "posted";
        }
    }

    public int numberOfDvds() {
        return dvdFacade.findAll().size();
    }

    public String findDvd() {
        this.dvdList = dvdFacade.findByName(sesion.getToFind());
        if (this.dvdList.isEmpty()) {
            this.dvdList = dvdFacade.findDvdByAuthor(sesion.getToFind());
            if (this.dvdList.isEmpty()) {
                this.dvdList = dvdFacade.findDvdByDirector(sesion.getToFind());
                if (this.dvdList.isEmpty()) {
                    return "nothing";
                } else {
                    return "found";
                }
            } else {
                return "found";
            }
        } else {
            return "found";
        }
    }

//    public String findByAuthor(){
//        if (dvdFacade.findDvdByAuthor(toFind) == null){
//            return "nothing";
//        }else{
//            this.author.setDvdList(dvdFacade.findDvdByAuthor(toFind));
//            this.dvdList = this.author.getDvdList();
//            return "found";
//        }
//        
//    }
//    
//    public String findByDirector(){
//        this.director.setDvdList(dvdFacade.findDvdByDirector(toFind));
//        if (dvdFacade.findDvdByDirector(toFind)==null){
//            return "nothing";
//        }else{
//            this.dvdList= this.director.getDvdList();
//            return "found";
//        }
//    }
}
